package com.pri.aa.enigma.controllers;

import com.pri.aa.enigma.models.Enigma;
import com.pri.aa.enigma.models.Kit;
import com.pri.aa.enigma.models.User;
import com.pri.aa.enigma.services.EnigmaService;
import com.pri.aa.enigma.services.KitService;
import com.pri.aa.enigma.services.PictureService;
import com.pri.aa.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class KitController {

    private KitService kitService;
    private EnigmaService enigmaService;
    private UserService userService;
    private PictureService pictureService;

    @Autowired
    public KitController(KitService kitService, EnigmaService enigmaService,
                         UserService userService, PictureService pictureService) {
        this.kitService = kitService;
        this.enigmaService = enigmaService;
        this.userService = userService;
        this.pictureService = pictureService;
    }

    @GetMapping("kit/generator")
    public String getGenView(Model model) {

        Kit kit = new Kit();
        Enigma enigma = new Enigma();
        enigmaService.save(enigma);

        List<Enigma> enigmas = new ArrayList<>();
        enigmas.add(enigma);

        kit.setUser(userService.getCurrentAuthUser());

        kit.setEnigmas(enigmas);

        kit.setUuid(UUID.randomUUID().toString());

        kitService.save(kit);

        model.addAttribute("kit", kit);
        return "kit/generator";
    }

    @GetMapping("kit/enigma/add/{id}")
    public String addEnigmaToKit(@PathVariable("id") Long id, Model model) {
        Optional<Kit> kit = kitService.getById(id);

        if (kit.isPresent()) {
            if (kit.get().getEnigmas().size() < 5 &&
                    kit.get().getUser().getUsername() ==
                            userService.getCurrentAuthUser().getUsername()) {
                Enigma enigma = new Enigma();
                enigmaService.save(enigma);

                kit.get().getEnigmas().add(enigma);
                kitService.save(kit.get());
                model.addAttribute("kit", kit.get());
            } else {
                model.addAttribute("errorAddKit", "Максимальное число энигм в ките - 5");
                model.addAttribute("kit", kit.get());
            }

        } else {
            return "redirect:/kit/generator";
        }

        return "kit/generator";
    }

    @PostMapping("kit/save")
    public String saveKit(@ModelAttribute Kit kit, Model model) {
        if (kit.getEnigmas() != null) {
            for (Enigma enigma : kit.getEnigmas()){
                if (enigma.getImg().isPresent() &&
                        enigma.getImg().get().getSize() != 0) {
                    Optional<String> filePath =
                            pictureService.savePictureLocaly(
                                    enigma.getImg().get(),
                                    pictureService.getEnigmaDir()
                            );
                    if (filePath.isPresent()) {
                        enigma.setImgLink(filePath.get());
                    } else {
                        enigma.setImgLink("nophoto.png");
                    }
                }
            }

            enigmaService.saveAll(kit.getEnigmas());
        }

        kit.setUser(userService.getCurrentAuthUser());
        kitService.save(kit);

        return "redirect:/kit/map";
    }

    @GetMapping("kit/map")
    public String getKitMapView(Model model) {
        User curUser = userService.getCurrentAuthUser();
        List<Kit> kits = kitService.getAllByUser(curUser);
        model.addAttribute("username", curUser.getUsername());
        model.addAttribute("kits", kits);
        model.addAttribute("ava", pictureService.getPictureString(
                pictureService.getAvaDir(),
                curUser.getPhotoLink()).orElse("no photo"));
        return "kit/map";
    }

    @GetMapping("kit/best")
    public String getKitBestView() {
        return "kit/best";
    }

    @GetMapping("kit/random")
    public String getKitRandom() {
        return "kit/random";
    }

    @GetMapping("kit/delete")
    public String deleteKit(@RequestParam(value = "id") Long id) {
        Optional<Kit> curKit = kitService.getById(id);
        User curUser = userService.getCurrentAuthUser();
        if (curKit.isPresent() &&
                curUser.getUsername() == curKit.get().getUser().getUsername())
        kitService.delete(id);
        return "redirect:/kit/map";
    }

    @GetMapping("kit/exec")
    public String getKitById(@RequestParam Optional<String> id,
                             Model model) {
        if (id.isPresent()) {
            Optional<Kit> kit = kitService.getByUuid(id.get());
            if (kit.isPresent()) {
                model.addAttribute("kit", kit.get());
                return "kit/random";
            } else {
                model.addAttribute("linkError", "Кита с такой ссылкой нет");
            }
        }

        return "index";
    }

    @GetMapping("kit/edit")
    public String editKitById(@RequestParam Optional<String> uuid,
                              Model model) {
        if (uuid.isPresent()) {
            Optional<Kit> kit = kitService.getByUuid(uuid.get());
            if (kit.isPresent()) {
                model.addAttribute("kit", kit.get());
                return "kit/edit";
            }
        }

        return "redirect:/profile";
    }
}
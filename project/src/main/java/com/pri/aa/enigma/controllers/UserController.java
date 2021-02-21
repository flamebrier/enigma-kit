package com.pri.aa.enigma.controllers;

import com.pri.aa.enigma.models.User;
import com.pri.aa.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Controller
public class UserController {
    private UserService userService;
    @Value("${proj.basedir}")
    private String projectPath;
    private String pathToAvatars = "/src/main/resources/static/img/ava/";

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getPersonalView(Principal principal, Model model) {
        User curUser = userService.findByUsername(principal.getName());
        model.addAttribute("userForm", curUser);
        model.addAttribute("ava",
                getPhotoBase64(curUser.getPhotoLink()).orElse("no photo"));
        return "/user/profile";
    }

    @GetMapping(value = "/getPhoto",
            produces = {MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public Optional<String> getPhotoBase64(String photoLink) {
        File file = new File(projectPath + pathToAvatars + photoLink);
        Optional<String> image;
        try {
            image = Optional.of(Base64Utils.encodeToString(
                    new FileInputStream(
                            new File(projectPath +
                                    pathToAvatars + photoLink)).readAllBytes()));
        } catch (Exception e) {
            try {
                image = Optional.of(
                    Base64Utils.encodeToString(
                        new FileInputStream(
                            new File(projectPath +
                                    pathToAvatars + "nophoto.png")).readAllBytes()
                    )
                );
            } catch (Exception ex) {
                image = Optional.empty();
            }
        }

        return image;
    }

    @PostMapping("/user/update")
    public String updateUser(User newUser, Principal principal,
                             @RequestParam("ava")MultipartFile file,
                             Model model) throws IOException {
        User userFromDb = userService.findByUsername(principal.getName());

        if (newUser.getPassword().isBlank() &&
                file.getOriginalFilename().isBlank()) {
            model.addAttribute("nonDataError", "Нет данных для обновления");
            User curUser = userService.findByUsername(principal.getName());
            model.addAttribute("userForm", curUser);
            return "/user/profile";
        }

        if (!newUser.getPassword().isBlank() &&
                newUser.getPassword().equals(newUser.getPasswordConfirm())) {
            userFromDb.setPassword(newUser.getPassword());
            userFromDb.setPasswordConfirm(newUser.getPasswordConfirm());
        }

        if (file.getSize() != 0) {
            String uploadPath = projectPath + pathToAvatars;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String filePath = uuidFile + file.getOriginalFilename()
                    .substring(file.getOriginalFilename().lastIndexOf('.'));
            /*String resPath = uploadDir + "/" + filePath;
            file.transferTo(new File((resPath)));*/
            Files.write(Path.of(uploadPath + filePath), file.getBytes());
            userFromDb.setPhotoLink(filePath);
        }

        userService.update(userFromDb);
        return "redirect:/profile";
    }

    @GetMapping("/registration")
    public String getRegistrForm(Model model) {
        model.addAttribute("userForm", new User());
        return "/user/registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("userForm") @Valid User userForm,
                               BindingResult bindingResult, Model model) {
        boolean hasErrors = false;

        if (bindingResult.hasErrors()) {
            hasErrors = true;
        }

        if (userService.existsByUsername(userForm.getUsername())) {
            model.addAttribute("usernameError", "Пользователь с данным логином уже зарегистрирован");
            hasErrors = true;
        }

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            hasErrors = true;
        }

        if (hasErrors) {
            return "/user/registration";
        }
        userService.save(userForm);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "/user/login";
    }
}
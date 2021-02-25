package com.pri.aa.enigma.controllers;

import com.pri.aa.enigma.models.User;
import com.pri.aa.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class HomeController {

    @Value("${proj.basedir}")
    private String basedir;

    @Autowired
    private UserService  userService;

    @GetMapping("/")
    public String getHelloPage() {
//        userService.save(new User("lili", "123"));
        return "index";
    }

    @GetMapping("/auth/repo")
    public String getAuthInfo(Model model) {
//        model.addAttribute("output", "../../img/ava/" + "flamebrier.jpg");
        return "secret/ha";
    }

    @PostMapping("/photo")
    public String sendPhoto(@RequestParam("ava") MultipartFile file) throws IOException {
//        if (file != null) {
//            File uploadDir = new File(basedir + "/src/main/resources/static/img/ava/");
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            String uuidFile = UUID.randomUUID().toString();
//            String resPath = uploadDir + "/" + uuidFile + file.getOriginalFilename()
//                    .substring(file.getOriginalFilename().lastIndexOf('.'));
//            file.transferTo(new File((resPath)));
//        }
        return "redirect:/auth/repo";
    }
}
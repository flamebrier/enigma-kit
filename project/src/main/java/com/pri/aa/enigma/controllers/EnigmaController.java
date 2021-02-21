package com.pri.aa.enigma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnigmaController {

    @GetMapping("/enigma/all")
    public String getAllEnigmaView() {
        return "/enigma/all";
    }

    @GetMapping("/enigma/create")
    public String getCreateEnigmaView() {
        return "/enigma/create";
    }
}
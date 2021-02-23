package com.pri.aa.enigma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KitController {

    @GetMapping("kit/generator")
    public String getGenView() {
        return "kit/generator";
    }

    @GetMapping("kit/map")
    public String getKitMapView() {
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
}
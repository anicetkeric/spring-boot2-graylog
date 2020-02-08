package com.logging.springboot2graylog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @GetMapping("/app/status")
    public String controllerStatus() {
        return "Service controller is up";
    }
}

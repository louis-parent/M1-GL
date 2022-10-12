package fr.poly.mtp.ig5.iwa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @GetMapping("registration")
    public String getRegistration() {
        return "registration";
    }
    @PostMapping("registration")
    public String addRegistration() {
        // ...
        return "registration";
    }
}

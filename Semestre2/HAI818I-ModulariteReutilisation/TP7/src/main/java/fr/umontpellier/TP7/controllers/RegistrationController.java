package fr.umontpellier.TP7.controllers;

import fr.umontpellier.TP7.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController
{
    @GetMapping("registration")
    public String getRegistration(@ModelAttribute("registration") Registration registration)
    {
        System.out.println("Register get");
        return "register";
    }

    @PostMapping("registration")
    public String addRegistration(@ModelAttribute("registration") Registration registration)
    {
        System.out.println( "Registration : " + registration.getEmail() + " " + registration.getPrenom() + " " + registration.getNom()) ;
        return "redirect:registration";
    }
}

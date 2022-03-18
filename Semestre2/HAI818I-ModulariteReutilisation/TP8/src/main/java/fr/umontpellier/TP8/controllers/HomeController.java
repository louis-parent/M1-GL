package fr.umontpellier.TP8.controllers;

import fr.umontpellier.TP8.models.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController
{
    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    @RequestMapping("/")
    public String version()
    {
        return this.appVersion;
    }
}

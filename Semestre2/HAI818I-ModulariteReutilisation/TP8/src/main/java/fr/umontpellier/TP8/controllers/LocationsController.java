package fr.umontpellier.TP8.controllers;

import fr.umontpellier.TP8.models.Location;
import fr.umontpellier.TP8.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/locations")
@RestController
public class LocationsController
{
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    public List<Location> list()
    {
        return locationRepository.findAll();
    }
}

package com.nikola.sneakershop.web;

import com.nikola.sneakershop.model.Manufacturer;
import com.nikola.sneakershop.service.application.ManufacturerApplicationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
@CrossOrigin(origins = "http://localhost:3000")
public class ManufacturerController {
    private final ManufacturerApplicationService manufacturerApplicationService;

    public ManufacturerController(ManufacturerApplicationService manufacturerApplicationService) {
        this.manufacturerApplicationService = manufacturerApplicationService;
    }

    @GetMapping
    public List<Manufacturer> getAllManufacturers() {
        return this.manufacturerApplicationService.listAll();
    }
}

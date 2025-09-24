package com.nikola.sneakershop.service.application.impl;

import com.nikola.sneakershop.model.Manufacturer;
import com.nikola.sneakershop.service.application.ManufacturerApplicationService;
import com.nikola.sneakershop.service.domain.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerApplicationServiceImpl implements ManufacturerApplicationService {
    private ManufacturerService manufacturerService;

    @Autowired
    public void setManufacturerService(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Override
    public List<Manufacturer> listAll() {
        return this.manufacturerService.listAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerService.findById(id);
    }

    @Override
    public Optional<Manufacturer> findByName(String name) {
        return this.manufacturerService.findByName(name);
    }

    @Override
    public Optional<Manufacturer> save(String name) {
        return this.manufacturerService.save(name);
    }

    @Override
    public Optional<Manufacturer> update(Long id, String name) {
        return this.manufacturerService.update(id, name);
    }

    @Override
    public void deleteById(Long id) {
        this.manufacturerService.deleteById(id);
    }
}

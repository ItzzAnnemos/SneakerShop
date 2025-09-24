package com.nikola.sneakershop.service.domain.impl;

import com.nikola.sneakershop.model.Manufacturer;
import com.nikola.sneakershop.repository.ManufacturerRepository;
import com.nikola.sneakershop.service.domain.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> listAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> findByName(String name) {
        return this.manufacturerRepository.findByName(name);
    }

    @Override
    public Optional<Manufacturer> save(Manufacturer manufacturer) {
        return Optional.of(this.manufacturerRepository.save(manufacturer));
    }

    @Override
    public Optional<Manufacturer> update(Long id, Manufacturer manufacturer) {
        return manufacturerRepository.findById(id).map(existingManufacturer -> {
            if (manufacturer.getName() != null) {
                existingManufacturer.setName(manufacturer.getName());
            }

            return this.manufacturerRepository.save(existingManufacturer);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.manufacturerRepository.deleteById(id);
    }
}

package com.nikola.sneakershop.service.application.impl;

import com.nikola.sneakershop.model.dto.CreateManufacturerDto;
import com.nikola.sneakershop.model.dto.DisplayManufacturerDto;
import com.nikola.sneakershop.service.application.ManufacturerApplicationService;
import com.nikola.sneakershop.service.domain.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManufacturerApplicationServiceImpl implements ManufacturerApplicationService {
    private ManufacturerService manufacturerService;

    @Autowired
    public void setManufacturerService(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Override
    public List<DisplayManufacturerDto> listAll() {
        return this.manufacturerService.listAll().stream()
                .map(DisplayManufacturerDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayManufacturerDto> findById(Long id) {
        return this.manufacturerService.findById(id).map(DisplayManufacturerDto::from);
    }

    @Override
    public Optional<DisplayManufacturerDto> findByName(String name) {
        return this.manufacturerService.findByName(name).map(DisplayManufacturerDto::from);
    }

    @Override
    public Optional<DisplayManufacturerDto> save(CreateManufacturerDto createManufacturerDto) {
        return this.manufacturerService.save(createManufacturerDto.toManufacturer()).map(DisplayManufacturerDto::from);
    }

    @Override
    public Optional<DisplayManufacturerDto> update(Long id, CreateManufacturerDto createManufacturerDto) {
        return this.manufacturerService.update(id, createManufacturerDto.toManufacturer()).map(DisplayManufacturerDto::from);
    }

    @Override
    public void deleteById(Long id) {
        this.manufacturerService.deleteById(id);
    }
}

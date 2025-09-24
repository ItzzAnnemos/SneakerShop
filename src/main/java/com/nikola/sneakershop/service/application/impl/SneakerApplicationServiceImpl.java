package com.nikola.sneakershop.service.application.impl;

import com.nikola.sneakershop.model.Manufacturer;
import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.dto.CreateSneakerDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerDetailsDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerListDto;
import com.nikola.sneakershop.model.dto.SneakerSizeDto;
import com.nikola.sneakershop.service.application.SneakerApplicationService;
import com.nikola.sneakershop.service.domain.ManufacturerService;
import com.nikola.sneakershop.service.domain.SneakerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SneakerApplicationServiceImpl implements SneakerApplicationService {
    private final SneakerService sneakerService;
    private final ManufacturerService manufacturerService;

    public SneakerApplicationServiceImpl(SneakerService sneakerService, ManufacturerService manufacturerService) {
        this.sneakerService = sneakerService;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public List<DisplaySneakerListDto> listAll() {
        return this.sneakerService.listAll().stream().map(DisplaySneakerListDto::from).collect(Collectors.toList());
    }

    @Override
    public Page<DisplaySneakerListDto> findAll(Pageable pageable) {
        return this.sneakerService.findAll(pageable).map(DisplaySneakerListDto::from);
    }

    @Override
    public Optional<DisplaySneakerDetailsDto> findById(Long id) {
        return this.sneakerService.findById(id).map(DisplaySneakerDetailsDto::from);
    }

    @Override
    public List<Sneaker> filterSneakers(Map<String, Object> filters) {
        return List.of();
    }

    @Override
    public Optional<DisplaySneakerDetailsDto> save(CreateSneakerDto sneaker) {
        Optional<Manufacturer> manufacturer = manufacturerService.findById(sneaker.manufacturerId());

        return manufacturer.flatMap(value -> sneakerService.save(sneaker.toSneaker(value))
                .map(DisplaySneakerDetailsDto::from));
    }

    @Override
    public Optional<DisplaySneakerDetailsDto> update(Long id, CreateSneakerDto sneaker) {
        Optional<Manufacturer> manufacturer = manufacturerService.findById(sneaker.manufacturerId());

        return sneakerService.update(id, sneaker.toSneaker(manufacturer.get()))
                .map(DisplaySneakerDetailsDto::from);
    }

    @Override
    public Optional<DisplaySneakerDetailsDto> updateSizes(Long id, List<SneakerSizeDto> sneakerSizes) {
        return Optional.empty();
    }

    @Override
    public Optional<DisplaySneakerDetailsDto> sellSneaker(Long id, int size, int quantity) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        sneakerService.deleteById(id);
    }
}
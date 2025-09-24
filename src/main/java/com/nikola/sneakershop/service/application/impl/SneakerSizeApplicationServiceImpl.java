package com.nikola.sneakershop.service.application.impl;

import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.dto.CreateSneakerSizeDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerSizeDto;
import com.nikola.sneakershop.model.dto.EnumValueDto;
import com.nikola.sneakershop.service.application.SneakerSizeApplicationService;
import com.nikola.sneakershop.service.domain.SneakerService;
import com.nikola.sneakershop.service.domain.SneakerSizeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SneakerSizeApplicationServiceImpl implements SneakerSizeApplicationService {
    private final SneakerSizeService sneakerSizeService;
    private final SneakerService sneakerService;

    public SneakerSizeApplicationServiceImpl(SneakerSizeService sneakerSizeService, SneakerService sneakerService) {
        this.sneakerSizeService = sneakerSizeService;
        this.sneakerService = sneakerService;
    }

    @Override
    public List<DisplaySneakerSizeDto> listAll() {
        return this.sneakerSizeService.listAll().stream().map(DisplaySneakerSizeDto::from).collect(Collectors.toList());
    }

    @Override
    public List<EnumValueDto> getAvailableSizes() {
        return this.sneakerSizeService.getAvailableSizes();
    }

    @Override
    public Optional<DisplaySneakerSizeDto> getSneakerSizeBySneakerIdAndSize(Long id, int size) {
        return this.sneakerSizeService.getSneakerSizeBySneakerIdAndSize(id, size).map(DisplaySneakerSizeDto::from);
    }

    @Override
    public Optional<DisplaySneakerSizeDto> save(CreateSneakerSizeDto createSneakerSizeDto) {
        Optional<Sneaker> sneaker = sneakerService.findById(createSneakerSizeDto.sneakerId());

        if (sneaker.isPresent()) {
            return this.sneakerSizeService.save(createSneakerSizeDto.toSneakerSize(sneaker.get()))
                    .map(DisplaySneakerSizeDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplaySneakerSizeDto> update(Long id, CreateSneakerSizeDto createSneakerSizeDto) {
        Optional<Sneaker> sneaker = sneakerService.findById(createSneakerSizeDto.sneakerId());

        return sneakerSizeService.update(id, createSneakerSizeDto.toSneakerSize(sneaker.orElse(null)))
                .map(DisplaySneakerSizeDto::from);
    }

    @Override
    public void delete(Long id) {

    }
}

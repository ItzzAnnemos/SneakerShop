package com.nikola.sneakershop.service.application.impl;

import com.nikola.sneakershop.model.SneakerSize;
import com.nikola.sneakershop.model.dto.EnumValueDto;
import com.nikola.sneakershop.service.application.SneakerSizeApplicationService;
import com.nikola.sneakershop.service.domain.SneakerSizeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SneakerSizeApplicationServiceImpl implements SneakerSizeApplicationService {
    private final SneakerSizeService sneakerSizeService;

    public SneakerSizeApplicationServiceImpl(SneakerSizeService sneakerSizeService) {
        this.sneakerSizeService = sneakerSizeService;
    }

    @Override
    public List<SneakerSize> listAll() {
        return this.sneakerSizeService.listAll();
    }

    @Override
    public List<EnumValueDto> getAvailableSizes() {
        return this.sneakerSizeService.getAvailableSizes();
    }

    @Override
    public Optional<SneakerSize> getSneakerSizeBySneakerIdAndSize(Long id, int size) {
        return this.sneakerSizeService.getSneakerSizeBySneakerIdAndSize(id, size);
    }
}

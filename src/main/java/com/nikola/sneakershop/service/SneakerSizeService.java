package com.nikola.sneakershop.service;

import com.nikola.sneakershop.model.SneakerSize;
import com.nikola.sneakershop.model.dto.EnumValueDto;

import java.util.List;
import java.util.Optional;

public interface SneakerSizeService {
    List<SneakerSize> listAll();

    List<EnumValueDto> getAvailableSizes();

    Optional<SneakerSize> getSneakerSizeBySneakerIdAndSize(Long id, int size);
}

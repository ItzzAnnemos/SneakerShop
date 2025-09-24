package com.nikola.sneakershop.service.application;

import com.nikola.sneakershop.model.SneakerSize;
import com.nikola.sneakershop.model.dto.EnumValueDto;

import java.util.List;
import java.util.Optional;

public interface SneakerSizeApplicationService {
    List<SneakerSize> listAll();

    List<EnumValueDto> getAvailableSizes();

    Optional<SneakerSize> getSneakerSizeBySneakerIdAndSize(Long id, int size);
}

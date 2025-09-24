package com.nikola.sneakershop.service.application;

import com.nikola.sneakershop.model.SneakerSize;
import com.nikola.sneakershop.model.dto.CreateSneakerSizeDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerSizeDto;
import com.nikola.sneakershop.model.dto.EnumValueDto;

import java.util.List;
import java.util.Optional;

public interface SneakerSizeApplicationService {
    List<DisplaySneakerSizeDto> listAll();

    List<EnumValueDto> getAvailableSizes();

    Optional<DisplaySneakerSizeDto> getSneakerSizeBySneakerIdAndSize(Long id, int size);

    Optional<DisplaySneakerSizeDto> save(CreateSneakerSizeDto createSneakerSizeDto);

    Optional<DisplaySneakerSizeDto> update(Long id, CreateSneakerSizeDto createSneakerSizeDto);

    void delete(Long id);
}

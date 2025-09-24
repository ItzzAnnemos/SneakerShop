package com.nikola.sneakershop.service.application;

import com.nikola.sneakershop.model.dto.CreateManufacturerDto;
import com.nikola.sneakershop.model.dto.DisplayManufacturerDto;

import java.util.List;
import java.util.Optional;

public interface ManufacturerApplicationService {
    List<DisplayManufacturerDto> listAll();

    Optional<DisplayManufacturerDto> findById(Long id);

    Optional<DisplayManufacturerDto> findByName(String name);

    Optional<DisplayManufacturerDto> save(CreateManufacturerDto createManufacturerDto);

    Optional<DisplayManufacturerDto> update(Long id, CreateManufacturerDto createManufacturerDto);

    void deleteById(Long id);
}

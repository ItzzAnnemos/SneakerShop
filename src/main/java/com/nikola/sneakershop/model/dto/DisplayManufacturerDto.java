package com.nikola.sneakershop.model.dto;

import com.nikola.sneakershop.model.Manufacturer;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayManufacturerDto(
        Long id,
        String name
) {
    public static DisplayManufacturerDto from(Manufacturer manufacturer) {
        return new DisplayManufacturerDto(manufacturer.getId(), manufacturer.getName());
    }

    public static List<DisplayManufacturerDto> from(List<Manufacturer> list) {
        return list.stream().map(DisplayManufacturerDto::from).collect(Collectors.toList());
    }

    public Manufacturer toManufacturer() {
        return new Manufacturer(name);
    }
}

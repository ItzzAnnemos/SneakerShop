package com.nikola.sneakershop.model.dto;

import com.nikola.sneakershop.model.Manufacturer;

import java.util.List;
import java.util.stream.Collectors;

public record CreateManufacturerDto(String name) {

    public static CreateManufacturerDto from(Manufacturer manufacturer) {
        return new CreateManufacturerDto(manufacturer.getName());
    }

    public static List<CreateManufacturerDto> from(List<Manufacturer> manufacturers) {
        return manufacturers.stream().map(CreateManufacturerDto::from).collect(Collectors.toList());
    }

    public Manufacturer toManufacturer() {
        return new Manufacturer(this.name);
    }
}

package com.nikola.sneakershop.model.dto;

import com.nikola.sneakershop.model.Manufacturer;
import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.SneakerSize;
import com.nikola.sneakershop.model.enumerations.Color;
import com.nikola.sneakershop.model.enumerations.Gender;
import com.nikola.sneakershop.model.enumerations.Purpose;

import java.util.List;
import java.util.stream.Collectors;

public record DisplaySneakerDetailsDto(
        Long id,
        Long manufacturerId,
        String name,
        double price,
        Gender gender,
        Purpose purpose,
        Color color,
        List<String> images,
        List<Long> sizesId) {

    public static DisplaySneakerDetailsDto from(Sneaker sneaker) {
        return new DisplaySneakerDetailsDto(
                sneaker.getId(),
                sneaker.getManufacturer().getId(),
                sneaker.getName(),
                sneaker.getPrice(),
                sneaker.getGender(),
                sneaker.getPurpose(),
                sneaker.getColor(),
                sneaker.getImages(),
                sneaker.getSizes().stream().map(SneakerSize::getId).toList()
        );
    }

    public static List<DisplaySneakerDetailsDto> from(List<Sneaker> sneakers) {
        return sneakers.stream().map(DisplaySneakerDetailsDto::from).collect(Collectors.toList());
    }

    public Sneaker toSneaker(Manufacturer manufacturer) {
        return new Sneaker(manufacturer, this.name, this.price, this.gender, this.purpose, this.color, this.images);
    }
}

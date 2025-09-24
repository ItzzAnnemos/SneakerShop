package com.nikola.sneakershop.model.dto;

import com.nikola.sneakershop.model.Sneaker;

import java.util.List;
import java.util.stream.Collectors;

public record DisplaySneakerListDto(
        Long id,
        Long manufacturerId,
        String name,
        double price,
        String image) {

    public static DisplaySneakerListDto from(Sneaker sneaker) {
        return new DisplaySneakerListDto(
                sneaker.getId(),
                sneaker.getManufacturer().getId(),
                sneaker.getName(),
                sneaker.getPrice(),
                sneaker.getImages().get(0)
        );
    }

    public static List<DisplaySneakerListDto> from(List<Sneaker> sneakers) {
        return sneakers.stream().map(DisplaySneakerListDto::from).collect(Collectors.toList());
    }
}

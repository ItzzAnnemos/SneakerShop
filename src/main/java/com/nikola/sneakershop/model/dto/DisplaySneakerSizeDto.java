package com.nikola.sneakershop.model.dto;

import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.SneakerSize;

import java.util.List;
import java.util.stream.Collectors;

public record DisplaySneakerSizeDto(
        Long id,
        int size,
        int quantity,
        Long sneakerId
) {
    public static DisplaySneakerSizeDto from(SneakerSize sneakerSize) {
        return new DisplaySneakerSizeDto(
                sneakerSize.getId(),
                sneakerSize.getSize(),
                sneakerSize.getQuantity(),
                sneakerSize.getSneaker().getId());
    }

    public static List<DisplaySneakerSizeDto> from(List<SneakerSize> sneakerSizes) {
        return sneakerSizes.stream().map(DisplaySneakerSizeDto::from).collect(Collectors.toList());
    }

    public SneakerSize toSneakerSize(Sneaker sneaker) {
        return new SneakerSize(this.size, this.quantity, sneaker);
    }
}

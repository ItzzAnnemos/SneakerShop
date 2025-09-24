package com.nikola.sneakershop.model.dto;

import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.SneakerSize;

import java.util.List;
import java.util.stream.Collectors;

public record CreateSneakerSizeDto(
        int size,
        int quantity,
        Long sneakerId
) {
    public static CreateSneakerSizeDto from(SneakerSize sneakerSize) {
        return new CreateSneakerSizeDto(
                sneakerSize.getSize(),
                sneakerSize.getQuantity(),
                sneakerSize.getSneaker().getId()
        );
    }

    public static List<CreateSneakerSizeDto> from(List<SneakerSize> sneakerSizes) {
        return sneakerSizes.stream().map(CreateSneakerSizeDto::from).collect(Collectors.toList());
    }

    public SneakerSize toSneakerSize(Sneaker sneaker) {
        return new SneakerSize(this.size, this.quantity, sneaker);
    }
}

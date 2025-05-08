package com.nikola.sneakershop.model.dto;

import lombok.Data;

@Data
public class SneakerSizeDto {
    private int size;

    private int quantity;

    public SneakerSizeDto(int size, int quantity) {
        this.size = size;
        this.quantity = quantity;
    }
}

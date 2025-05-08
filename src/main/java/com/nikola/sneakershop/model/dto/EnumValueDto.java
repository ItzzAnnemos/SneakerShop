package com.nikola.sneakershop.model.dto;

import lombok.Data;

@Data
public class EnumValueDto {
    private String value;
    private String label;

    public EnumValueDto(String value, String label) {
        this.value = value;
        this.label = label;
    }
}

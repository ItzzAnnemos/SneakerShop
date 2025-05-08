package com.nikola.sneakershop.model.dto;

import com.nikola.sneakershop.model.SneakerSize;
import com.nikola.sneakershop.model.enumerations.Color;
import com.nikola.sneakershop.model.enumerations.Gender;
import com.nikola.sneakershop.model.enumerations.Purpose;
import lombok.Data;

import java.util.List;

@Data
public class SneakerDto {
    private Long manufacturer;

    private String name;

    private double price;

    private Gender gender;

    private Purpose purpose;

    private Color color;

    private List<String> images;

    private List<SneakerSize> sizes;
}

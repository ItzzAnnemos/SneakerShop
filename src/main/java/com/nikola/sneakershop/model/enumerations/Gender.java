package com.nikola.sneakershop.model.enumerations;


import lombok.Getter;

@Getter
public enum Gender {
    MEN("Men"),
    WOMEN("Women"),
    UNISEX("Unisex"),
    KIDS("Kids");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.name().toLowerCase();
    }
}

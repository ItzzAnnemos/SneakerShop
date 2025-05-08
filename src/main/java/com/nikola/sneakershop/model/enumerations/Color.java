package com.nikola.sneakershop.model.enumerations;

import lombok.Getter;

@Getter
public enum Color {
    BLACK("Black"),
    WHITE("White"),
    RED("Red"),
    BLUE("Blue"),
    GREEN("Green");

    private final String label;

    Color(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.name().toLowerCase();
    }
}

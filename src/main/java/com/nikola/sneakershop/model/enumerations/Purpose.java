package com.nikola.sneakershop.model.enumerations;

import lombok.Getter;

@Getter
public enum Purpose {
    RUNNING("Running"),
    TRAINING("Training"),
    CASUAL("Casual"),
    BASKETBALL("Basketball"),
    TENNIS("Tennis");

    private final String label;

    Purpose(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.name().toLowerCase();
    }
}

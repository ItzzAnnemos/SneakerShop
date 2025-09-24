package com.nikola.sneakershop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SneakerSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer size;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "sneaker_id")
    @JsonBackReference
    private Sneaker sneaker;

    public SneakerSize() {
    }

    public SneakerSize(int size, int quantity, Sneaker sneaker) {
        this.size = size;
        this.quantity = quantity;
        this.sneaker = sneaker;
    }
}

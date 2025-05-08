package com.nikola.sneakershop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nikola.sneakershop.model.enumerations.Color;
import com.nikola.sneakershop.model.enumerations.Gender;
import com.nikola.sneakershop.model.enumerations.Purpose;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Sneaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonManagedReference
    private Manufacturer manufacturer;

    private String name;

    private double price;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Enumerated(EnumType.STRING)
    private Color color;

    @ElementCollection
    private List<String> images;

    @OneToMany(mappedBy = "sneaker", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SneakerSize> sizes;

    public Sneaker () {}

    public Sneaker(Manufacturer manufacturer, String name, double price, Gender gender, Purpose purpose,
                   Color color, List<String> images) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.price = price;
        this.gender = gender;
        this.purpose = purpose;
        this.color = color;
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sneaker sneaker)) return false;
        return Objects.equals(id, sneaker.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

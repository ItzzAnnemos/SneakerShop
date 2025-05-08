package com.nikola.sneakershop.Bootstrap;

import com.nikola.sneakershop.model.Manufacturer;
import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.SneakerSize;
import com.nikola.sneakershop.model.enumerations.Color;
import com.nikola.sneakershop.model.enumerations.Gender;
import com.nikola.sneakershop.model.enumerations.Purpose;
import com.nikola.sneakershop.repository.ManufacturerRepository;
import com.nikola.sneakershop.repository.SneakerRepository;
import com.nikola.sneakershop.repository.SneakerSizeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    private static List<Sneaker> sneakers = null;
    private static List<SneakerSize> sneakerSizes = null;
    private static List<Manufacturer> manufacturers = null;

    private final SneakerRepository sneakerRepository;
    private final SneakerSizeRepository sneakerSizeRepository;
    private final ManufacturerRepository manufacturerRepository;

    public DataHolder(SneakerRepository sneakerRepository,
                      SneakerSizeRepository sneakerSizeRepository,
                      ManufacturerRepository manufacturerRepository) {
        this.sneakerRepository = sneakerRepository;
        this.sneakerSizeRepository = sneakerSizeRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @PostConstruct
    public void init(){
        manufacturers = new ArrayList<>();
        if (this.manufacturerRepository.count() == 0) {
            manufacturers.add(new Manufacturer("Nike"));
            manufacturers.add(new Manufacturer("Adidas"));

            manufacturerRepository.saveAll(manufacturers);
        } else {
            manufacturers = manufacturerRepository.findAll();
        }

        sneakers = new ArrayList<>();
        if (this.sneakerRepository.count() == 0) {
            sneakers.add(new Sneaker(manufacturers.get(0), "Jordan1 Low", 150.0, Gender.MEN,
                    Purpose.BASKETBALL, Color.BLUE, List.of("https://skstore.eu/storage/media/f1000/2023/nike/221813/air-jordan-1-low-553558-140-6540ef8c13751.jpg")));
            Sneaker sneaker = sneakers.get(0);
            sneaker.setSizes(List.of(new SneakerSize(43, 10, sneaker),
                    new SneakerSize(42, 8, sneaker),
                    new SneakerSize(41, 10, sneaker),
                    new SneakerSize(44, 5, sneaker)));

            sneakers.add(new Sneaker(manufacturers.get(0), "Air Force 1", 130.0, Gender.WOMEN,
                    Purpose.CASUAL, Color.WHITE, List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLIw0fW3bKolQWnc7gOdf9Qub7ceKTJ_Ay1g&s")));
            sneaker = sneakers.get(1);
            sneaker.setSizes(List.of(new SneakerSize(43, 10, sneaker),
                    new SneakerSize(42, 8, sneaker),
                    new SneakerSize(41, 10, sneaker),
                    new SneakerSize(44, 5, sneaker)));

            sneakerRepository.saveAll(sneakers);

            sneakers.add(new Sneaker(manufacturers.get(0), "Jordan1 Low", 150.0, Gender.MEN,
                    Purpose.BASKETBALL, Color.BLUE, List.of("https://skstore.eu/storage/media/f1000/2023/nike/221813/air-jordan-1-low-553558-140-6540ef8c13751.jpg")));
            sneaker = sneakers.get(0);
            sneaker.setSizes(List.of(new SneakerSize(43, 10, sneaker),
                    new SneakerSize(42, 8, sneaker),
                    new SneakerSize(41, 10, sneaker),
                    new SneakerSize(44, 5, sneaker)));

            sneakers.add(new Sneaker(manufacturers.get(0), "Air Force 1", 130.0, Gender.WOMEN,
                    Purpose.CASUAL, Color.WHITE, List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLIw0fW3bKolQWnc7gOdf9Qub7ceKTJ_Ay1g&s")));
            sneaker = sneakers.get(1);
            sneaker.setSizes(List.of(new SneakerSize(43, 10, sneaker),
                    new SneakerSize(42, 8, sneaker),
                    new SneakerSize(41, 10, sneaker),
                    new SneakerSize(44, 5, sneaker)));

            sneakers.add(new Sneaker(manufacturers.get(0), "Jordan1 Low", 150.0, Gender.MEN,
                    Purpose.BASKETBALL, Color.BLUE, List.of("https://skstore.eu/storage/media/f1000/2023/nike/221813/air-jordan-1-low-553558-140-6540ef8c13751.jpg")));
            sneaker = sneakers.get(0);
            sneaker.setSizes(List.of(new SneakerSize(43, 10, sneaker),
                    new SneakerSize(42, 8, sneaker),
                    new SneakerSize(41, 10, sneaker),
                    new SneakerSize(44, 5, sneaker)));

            sneakers.add(new Sneaker(manufacturers.get(0), "Air Force 1", 130.0, Gender.WOMEN,
                    Purpose.CASUAL, Color.WHITE, List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLIw0fW3bKolQWnc7gOdf9Qub7ceKTJ_Ay1g&s")));
            sneaker = sneakers.get(1);
            sneaker.setSizes(List.of(new SneakerSize(43, 10, sneaker),
                    new SneakerSize(42, 8, sneaker),
                    new SneakerSize(41, 10, sneaker),
                    new SneakerSize(44, 5, sneaker)));


            sneakerRepository.saveAll(sneakers);
        }
    }
}

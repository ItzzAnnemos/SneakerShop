package com.nikola.sneakershop.service;

import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.dto.SneakerDto;
import com.nikola.sneakershop.model.dto.SneakerSizeDto;

import java.util.*;


public interface SneakerService {
    List<Sneaker> listAll();

    Optional<Sneaker> findById(Long id);

    List<Sneaker> filterSneakers(Map<String, Object> filters);

    Optional<Sneaker> save(SneakerDto sneaker);

    Optional<Sneaker> update(Long id, SneakerDto sneaker);

    Optional<Sneaker> updateSizes(Long id, List<SneakerSizeDto> sneakerSizes);

    Optional<Sneaker> sellSneaker(Long id, int size, int quantity);

    void deleteById(Long id);
}

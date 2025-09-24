package com.nikola.sneakershop.service.domain;

import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.dto.CreateSneakerDto;
import com.nikola.sneakershop.model.dto.SneakerSizeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;


public interface SneakerService {
    List<Sneaker> listAll();

    Page<Sneaker> findAll(Pageable pageable);

    Optional<Sneaker> findById(Long id);

    List<Sneaker> filterSneakers(Map<String, Object> filters);

    Optional<Sneaker> save(Sneaker sneaker);

    Optional<Sneaker> update(Long id, Sneaker sneaker);

    Optional<Sneaker> updateSizes(Long id, List<SneakerSizeDto> sneakerSizes);

    Optional<Sneaker> sellSneaker(Long id, int size, int quantity);

    void deleteById(Long id);
}

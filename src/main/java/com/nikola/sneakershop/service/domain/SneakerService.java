package com.nikola.sneakershop.service.domain;

import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.dto.CreateSneakerSizeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

public interface SneakerService {
    List<Sneaker> listAll();

    Page<Sneaker> findAll(Specification<Sneaker> filter, Pageable pageable);

    Optional<Sneaker> findById(Long id);

    Optional<Sneaker> save(Sneaker sneaker);

    Optional<Sneaker> update(Long id, Sneaker sneaker);

    Optional<Sneaker> updateSizes(Long id, List<CreateSneakerSizeDto> sneakerSizes);

    Optional<Sneaker> sellSneaker(Long id, int size, int quantity);

    void deleteById(Long id);
}

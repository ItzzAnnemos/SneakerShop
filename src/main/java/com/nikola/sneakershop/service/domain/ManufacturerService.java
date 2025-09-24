package com.nikola.sneakershop.service.domain;

import com.nikola.sneakershop.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    List<Manufacturer> listAll();

    Optional<Manufacturer> findById(Long id);
    Optional<Manufacturer> findByName(String name);
    Optional<Manufacturer> save(String name);
    Optional<Manufacturer> update(Long id, String name);
    void deleteById(Long id);

}

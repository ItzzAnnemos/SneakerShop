package com.nikola.sneakershop.service.application.impl;

import com.nikola.sneakershop.model.Manufacturer;
import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.dto.CreateSneakerDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerDetailsDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerListDto;
import com.nikola.sneakershop.model.dto.CreateSneakerSizeDto;
import com.nikola.sneakershop.model.enumerations.Color;
import com.nikola.sneakershop.model.enumerations.Gender;
import com.nikola.sneakershop.model.enumerations.Purpose;
import com.nikola.sneakershop.service.application.SneakerApplicationService;
import com.nikola.sneakershop.service.domain.ManufacturerService;
import com.nikola.sneakershop.service.domain.SneakerService;
import com.nikola.sneakershop.service.specification.FieldFilterSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SneakerApplicationServiceImpl implements SneakerApplicationService {
    private final SneakerService sneakerService;
    private final ManufacturerService manufacturerService;

    public SneakerApplicationServiceImpl(SneakerService sneakerService, ManufacturerService manufacturerService) {
        this.sneakerService = sneakerService;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public List<DisplaySneakerListDto> listAll() {
        return this.sneakerService.listAll().stream().map(DisplaySneakerListDto::from).collect(Collectors.toList());
    }

    @Override
    public Page<DisplaySneakerListDto> findAll(String name,
                                               List<Long> manufacturerIds,
                                               List<Gender> genders,
                                               List<Purpose> purposes,
                                               List<Color> colors,
                                               List<Integer> sizes,
                                               Double minPrice,
                                               Double maxPrice,
                                               Pageable pageable) {
        Specification<Sneaker> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(FieldFilterSpecification.filterContainsText(Sneaker.class, "name", name));
        }

        if (manufacturerIds != null && !manufacturerIds.isEmpty()) {
            spec = spec.and(FieldFilterSpecification.filterIn(Sneaker.class, "manufacturer.id", manufacturerIds));
        }

        if (genders != null && !genders.isEmpty()) {
            spec = spec.and(FieldFilterSpecification.filterIn(Sneaker.class, "gender", genders));
        }

        if (purposes != null && !purposes.isEmpty()) {
            spec = spec.and(FieldFilterSpecification.filterIn(Sneaker.class, "purpose", purposes));
        }

        if (colors != null && !colors.isEmpty()) {
            spec = spec.and(FieldFilterSpecification.filterIn(Sneaker.class, "color", colors));
        }

        if (sizes != null) {
            spec = spec.and(FieldFilterSpecification.filterCollectionIn("sizes", "size", sizes));
        }

        if (minPrice != null || maxPrice != null) {
            spec = spec.and(FieldFilterSpecification.filterBetween(Sneaker.class, "price", minPrice, maxPrice));
        }

        return sneakerService.findAll(spec, pageable)
                .map(DisplaySneakerListDto::from);
    }

    @Override
    public Optional<DisplaySneakerDetailsDto> findById(Long id) {
        return this.sneakerService.findById(id).map(DisplaySneakerDetailsDto::from);
    }

    @Override
    public Optional<DisplaySneakerDetailsDto> save(CreateSneakerDto sneaker) {
        Optional<Manufacturer> manufacturer = manufacturerService.findById(sneaker.manufacturerId());

        return manufacturer.flatMap(value -> sneakerService.save(sneaker.toSneaker(value))
                .map(DisplaySneakerDetailsDto::from));
    }

    @Override
    public Optional<DisplaySneakerDetailsDto> update(Long id, CreateSneakerDto sneaker) {
        Optional<Manufacturer> manufacturer = manufacturerService.findById(sneaker.manufacturerId());

        return sneakerService.update(id, sneaker.toSneaker(manufacturer.get()))
                .map(DisplaySneakerDetailsDto::from);
    }

    @Override
    public Optional<DisplaySneakerDetailsDto> updateSizes(Long id, List<CreateSneakerSizeDto> sneakerSizes) {
        return Optional.empty();
    }

    @Override
    public Optional<DisplaySneakerDetailsDto> sellSneaker(Long id, int size, int quantity) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        sneakerService.deleteById(id);
    }
}
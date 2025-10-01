package com.nikola.sneakershop.service.domain.impl;

import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.SneakerSize;
import com.nikola.sneakershop.model.dto.CreateSneakerSizeDto;
import com.nikola.sneakershop.repository.SneakerRepository;
import com.nikola.sneakershop.service.domain.ManufacturerService;
import com.nikola.sneakershop.service.domain.SneakerService;
import com.nikola.sneakershop.service.domain.SneakerSizeService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SneakerServiceImpl implements SneakerService {
    private final SneakerRepository sneakerRepository;
    private final ManufacturerService manufacturerService;
    private final SneakerSizeService sneakerSizeService;

    public SneakerServiceImpl(SneakerRepository sneakerRepository, ManufacturerService manufacturerService, SneakerSizeService sneakerSizeService) {
        this.sneakerRepository = sneakerRepository;
        this.manufacturerService = manufacturerService;
        this.sneakerSizeService = sneakerSizeService;
    }

    @Override
    public List<Sneaker> listAll() {
        return this.sneakerRepository.findAll();
    }

    @Override
    public Page<Sneaker> findAll(Specification<Sneaker> filter, Pageable pageable) {
        return this.sneakerRepository.findAll(filter, pageable);
    }

    @Override
    public Optional<Sneaker> findById(Long id) {
        return this.sneakerRepository.findById(id);
    }

    @Override
    public Optional<Sneaker> save(Sneaker sneaker) {
        if (sneaker.getManufacturer() != null && manufacturerService.findById(sneaker.getManufacturer().getId()).isPresent()) {
            return Optional.of(this.sneakerRepository.save(
                    new Sneaker(manufacturerService.findById(sneaker.getManufacturer().getId()).get(), sneaker.getName(),
                            sneaker.getPrice(), sneaker.getGender(), sneaker.getPurpose(), sneaker.getColor(),
                            sneaker.getImages())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Sneaker> update(Long id, Sneaker sneaker) {
        return this.sneakerRepository.findById(id).map(existingSneaker -> {
            if (sneaker.getName() != null) {
                existingSneaker.setName(sneaker.getName());
            }
            if (sneaker.getManufacturer() != null
                    && manufacturerService.findById(sneaker.getManufacturer().getId()).isPresent()) {
                existingSneaker.setManufacturer(manufacturerService.findById(sneaker.getManufacturer().getId()).get());
            }
            if (sneaker.getPrice() != 0) {
                existingSneaker.setPrice(sneaker.getPrice());
            }
            if (sneaker.getGender() != null) {
                existingSneaker.setGender(sneaker.getGender());
            }
            if (sneaker.getPurpose() != null) {
                existingSneaker.setPurpose(sneaker.getPurpose());
            }
            if (sneaker.getColor() != null) {
                existingSneaker.setColor(sneaker.getColor());
            }
            if (sneaker.getImages() != null) {
                existingSneaker.setImages(sneaker.getImages());
            }
            return sneakerRepository.save(existingSneaker);
        });
    }

    @Override
    public Optional<Sneaker> updateSizes(Long id, List<CreateSneakerSizeDto> sneakerSizes) {
        Optional<Sneaker> sneakerOpt = this.findById(id);
        if (sneakerOpt.isEmpty()) {
            return Optional.empty();
        }

        Sneaker sneaker = sneakerOpt.get();
        Map<Integer, SneakerSize> existingSizesMap = new HashMap<>();

        for (SneakerSize size : sneaker.getSizes()) {
            existingSizesMap.put(size.getSize(), size);
        }

        for (CreateSneakerSizeDto sizeDto : sneakerSizes) {
            if (existingSizesMap.containsKey(sizeDto.size())) {
                SneakerSize existingSize = existingSizesMap.get(sizeDto.size());
                existingSize.setQuantity(sizeDto.quantity());
            } else {
                SneakerSize newSize = new SneakerSize(sizeDto.size(), sizeDto.quantity(), sneaker);
                sneaker.getSizes().add(newSize);
            }
        }

        return Optional.of(this.sneakerRepository.save(sneaker));
    }

    @Transactional
    @Override
    public Optional<Sneaker> sellSneaker(Long id, int size, int quantity) {
        Optional<SneakerSize> sneakerSizeOpt = sneakerSizeService.getSneakerSizeBySneakerIdAndSize(id, size);
        if (sneakerSizeOpt.isEmpty()) {
            return Optional.empty();
        }

        SneakerSize sneakerSize = sneakerSizeOpt.get();

        if (sneakerSize.getQuantity() < quantity) {
            return Optional.empty();
        }

        return Optional.empty();
    }


    @Override
    public void deleteById(Long id) {
        this.sneakerRepository.deleteById(id);
    }
}

package com.nikola.sneakershop.service.domain.impl;

import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.SneakerSize;
import com.nikola.sneakershop.model.dto.EnumValueDto;
import com.nikola.sneakershop.repository.SneakerSizeRepository;
import com.nikola.sneakershop.service.domain.SneakerService;
import com.nikola.sneakershop.service.domain.SneakerSizeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SneakerSizeServiceImpl implements SneakerSizeService {
    private final SneakerSizeRepository sneakerSizeRepository;
    private final SneakerService sneakerService;

    public SneakerSizeServiceImpl(SneakerSizeRepository sneakerSizeRepository, SneakerService sneakerService) {
        this.sneakerSizeRepository = sneakerSizeRepository;
        this.sneakerService = sneakerService;
    }

    @Override
    public List<SneakerSize> listAll() {
        return sneakerSizeRepository.findAll();
    }

    @Override
    public List<EnumValueDto> getAvailableSizes() {
        List<Integer> distinctSizes = sneakerSizeRepository.findAllDistinctBySize();

        return distinctSizes.stream()
                .map(size -> new EnumValueDto(size.toString(), size.toString()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SneakerSize> getSneakerSizeBySneakerIdAndSize(Long id, int size) {
        return sneakerSizeRepository.findBySneakerIdAndSize(id, size);
    }

    @Override
    public Optional<SneakerSize> save(SneakerSize sneakerSize) {
        if (sneakerSize.getSneaker() != null && sneakerService.findById(sneakerSize.getSneaker().getId()).isPresent()) {
            return Optional.of(sneakerSizeRepository.save(sneakerSize));
        }
        return Optional.empty();
    }

    @Override
    public Optional<SneakerSize> update(Long id, SneakerSize sneakerSize) {
        return sneakerSizeRepository.findById(id).map(existingSneakerSize -> {
            if (sneakerSize.getSize() != null) {
                existingSneakerSize.setSize(sneakerSize.getSize());
            }
            if (sneakerSize.getQuantity() != null) {
                existingSneakerSize.setQuantity(sneakerSize.getQuantity());
            }
            if (sneakerSize.getSneaker() != null && sneakerService.findById(sneakerSize.getSneaker().getId()).isPresent()) {
                existingSneakerSize.setSneaker(sneakerService.findById(sneakerSize.getSneaker().getId()).get());
            }
            return sneakerSizeRepository.save(existingSneakerSize);
        });
    }

    @Override
    public void delete(Long id) {
        this.sneakerSizeRepository.deleteById(id);
    }
}

package com.nikola.sneakershop.service.domain.impl;

import com.nikola.sneakershop.model.SneakerSize;
import com.nikola.sneakershop.model.dto.EnumValueDto;
import com.nikola.sneakershop.repository.SneakerSizeRepository;
import com.nikola.sneakershop.service.domain.SneakerSizeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SneakerSizeServiceImpl implements SneakerSizeService {
    private final SneakerSizeRepository sneakerSizeRepository;

    public SneakerSizeServiceImpl(SneakerSizeRepository sneakerSizeRepository) {
        this.sneakerSizeRepository = sneakerSizeRepository;
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
}

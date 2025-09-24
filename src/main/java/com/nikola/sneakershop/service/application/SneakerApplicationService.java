package com.nikola.sneakershop.service.application;

import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.dto.CreateSneakerDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerDetailsDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerListDto;
import com.nikola.sneakershop.model.dto.CreateSneakerSizeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface SneakerApplicationService {
    List<DisplaySneakerListDto> listAll();

    Page<DisplaySneakerListDto> findAll(Pageable pageable);

    Optional<DisplaySneakerDetailsDto> findById(Long id);

    List<Sneaker> filterSneakers(Map<String, Object> filters);

    Optional<DisplaySneakerDetailsDto> save(CreateSneakerDto sneaker);

    Optional<DisplaySneakerDetailsDto> update(Long id, CreateSneakerDto sneaker);

    Optional<DisplaySneakerDetailsDto> updateSizes(Long id, List<CreateSneakerSizeDto> sneakerSizes);

    Optional<DisplaySneakerDetailsDto> sellSneaker(Long id, int size, int quantity);

    void deleteById(Long id);
}

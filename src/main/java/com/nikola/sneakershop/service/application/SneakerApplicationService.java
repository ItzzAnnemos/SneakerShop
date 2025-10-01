package com.nikola.sneakershop.service.application;

import com.nikola.sneakershop.model.dto.CreateSneakerDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerDetailsDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerListDto;
import com.nikola.sneakershop.model.dto.CreateSneakerSizeDto;
import com.nikola.sneakershop.model.enumerations.Color;
import com.nikola.sneakershop.model.enumerations.Gender;
import com.nikola.sneakershop.model.enumerations.Purpose;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface SneakerApplicationService {
    List<DisplaySneakerListDto> listAll();

    Page<DisplaySneakerListDto> findAll(String name,
                                        List<Long> manufacturerIds,
                                        List<Gender> genders,
                                        List<Purpose> purposes,
                                        List<Color> colors,
                                        List<Integer> sizes,
                                        Double minPrice,
                                        Double maxPrice,
                                        Pageable pageable);

    Optional<DisplaySneakerDetailsDto> findById(Long id);

    Optional<DisplaySneakerDetailsDto> save(CreateSneakerDto sneaker);

    Optional<DisplaySneakerDetailsDto> update(Long id, CreateSneakerDto sneaker);

    Optional<DisplaySneakerDetailsDto> updateSizes(Long id, List<CreateSneakerSizeDto> sneakerSizes);

    Optional<DisplaySneakerDetailsDto> sellSneaker(Long id, int size, int quantity);

    void deleteById(Long id);
}

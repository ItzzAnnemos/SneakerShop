package com.nikola.sneakershop.service.domain.impl;

import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.SneakerSize;
import com.nikola.sneakershop.model.dto.CreateSneakerSizeDto;
import com.nikola.sneakershop.model.enumerations.Color;
import com.nikola.sneakershop.model.enumerations.Gender;
import com.nikola.sneakershop.model.enumerations.Purpose;
import com.nikola.sneakershop.repository.SneakerRepository;
import com.nikola.sneakershop.service.domain.ManufacturerService;
import com.nikola.sneakershop.service.domain.SneakerService;
import com.nikola.sneakershop.service.domain.SneakerSizeService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Sneaker> findAll(Pageable pageable) {
        return this.sneakerRepository.findAll(pageable);
    }

    @Override
    public Optional<Sneaker> findById(Long id) {
        return this.sneakerRepository.findById(id);
    }

    @Override
    public List<Sneaker> filterSneakers(Map<String, Object> filters) {
        String query = null;
        if (filters.containsKey("search") && filters.get("search") != null && !filters.get("search").toString().isEmpty()) {
            query = filters.get("search").toString();
        }

        // Safer handling of manufacturer
        List<String> manufacturers = null;
        if (filters.containsKey("manufacturers") && filters.get("manufacturers") != null) {
            try {
                manufacturers = new ArrayList<>();
                Object manObj = filters.get("manufacturers");

                if (manObj instanceof List) {
                    for (Object item : (List<?>) manObj) {
                        try {
                            manufacturers.add(item.toString());
                        } catch (IllegalArgumentException e) {
                            // Log invalid getColor value but continue processing
                            System.err.println("Invalid manufacturer value: " + item);
                        }
                    }
                }
            } catch (Exception e) {
                // Log the error but return empty list rather than failing
                System.err.println("Error processing manufacturers filters: " + e.getMessage());
                manufacturers = Collections.emptyList();
            }
        }

        // Safer handling of color filters
        List<Color> colors = null;
        if (filters.containsKey("colors") && filters.get("colors") != null) {
            try {
                colors = new ArrayList<>();
                Object colorObj = filters.get("colors");

                if (colorObj instanceof List) {
                    for (Object item : (List<?>) colorObj) {
                        try {
                            colors.add(Color.valueOf(item.toString().toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            // Log invalid color value but continue processing
                            System.err.println("Invalid color value: " + item);
                        }
                    }
                }
            } catch (Exception e) {
                // Log the error but return empty list rather than failing
                System.err.println("Error processing color filters: " + e.getMessage());
                colors = Collections.emptyList();
            }
        }

        // Safer handling of purpose filters
        List<Purpose> purposes = null;
        if (filters.containsKey("purposes") && filters.get("purposes") != null) {
            try {
                purposes = new ArrayList<>();
                Object purposeObj = filters.get("purposes");

                if (purposeObj instanceof List) {
                    for (Object item : (List<?>) purposeObj) {
                        try {
                            purposes.add(Purpose.valueOf(item.toString().toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            // Log invalid purpose value but continue processing
                            System.err.println("Invalid purpose value: " + item);
                        }
                    }
                }
            } catch (Exception e) {
                // Log the error but return empty list rather than failing
                System.err.println("Error processing purpose filters: " + e.getMessage());
                purposes = Collections.emptyList();
            }
        }

        // Safer handling of gender filters
        List<Gender> genders = null;
        if (filters.containsKey("genders") && filters.get("genders") != null) {
            try {
                genders = new ArrayList<>();
                Object genderObj = filters.get("genders");

                if (genderObj instanceof List) {
                    for (Object item : (List<?>) genderObj) {
                        try {
                            genders.add(Gender.valueOf(item.toString().toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            // Log invalid gender value but continue processing
                            System.err.println("Invalid gender value: " + item);
                        }
                    }
                }
            } catch (Exception e) {
                // Log the error but return empty list rather than failing
                System.err.println("Error processing gender filters: " + e.getMessage());
                genders = Collections.emptyList();
            }
        }

        // Safer handling of size filters
        List<Integer> sizes = null;
        if (filters.containsKey("sizes") && filters.get("sizes") != null) {
            try {
                sizes = new ArrayList<>();
                Object sizeObj = filters.get("sizes");

                if (sizeObj instanceof List) {
                    for (Object item : (List<?>) sizeObj) {
                        try {
                            sizes.add(Integer.parseInt(item.toString()));
                        } catch (NumberFormatException e) {
                            // Log invalid size value but continue processing
                            System.err.println("Invalid size value: " + item);
                        }
                    }
                }
            } catch (Exception e) {
                // Log the error but return empty list rather than failing
                System.err.println("Error processing size filters: " + e.getMessage());
                sizes = Collections.emptyList();
            }
        }

        // Safer handling of price range
        Double minPrice = null;
        Double maxPrice = null;
        if (filters.containsKey("price") && filters.get("price") != null) {
            try {
                Object priceObj = filters.get("price");

                if (priceObj instanceof Map) {
                    Map<?, ?> priceMap = (Map<?, ?>) priceObj;

                    if (priceMap.containsKey("min") && priceMap.get("min") != null) {
                        try {
                            minPrice = Double.parseDouble(priceMap.get("min").toString());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid min price value: " + priceMap.get("min"));
                        }
                    }

                    if (priceMap.containsKey("max") && priceMap.get("max") != null) {
                        try {
                            maxPrice = Double.parseDouble(priceMap.get("max").toString());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid max price value: " + priceMap.get("max"));
                        }
                    }
                }
            } catch (Exception e) {
                // Log the error but continue with null values
                System.err.println("Error processing price filters: " + e.getMessage());
            }
        }

        // Add logging to see what values are being sent to the repository
        System.out.println("Filtering with parameters: " +
                "query=" + query +
                ", manufacturers=" + manufacturers +
                ", colors=" + colors +
                ", purposes=" + purposes +
                ", genders=" + genders +
                ", sizes=" + sizes +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice);

        // Handle potential null return from repository
        List<Sneaker> results = sneakerRepository.findByCriteria(
                query, manufacturers, colors, purposes, genders, sizes, minPrice, maxPrice);

        return results != null ? results : Collections.emptyList();
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

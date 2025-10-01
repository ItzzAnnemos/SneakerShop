package com.nikola.sneakershop.web;

import com.nikola.sneakershop.model.dto.CreateSneakerDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerDetailsDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerListDto;
import com.nikola.sneakershop.model.dto.CreateSneakerSizeDto;
import com.nikola.sneakershop.model.enumerations.Color;
import com.nikola.sneakershop.model.enumerations.Gender;
import com.nikola.sneakershop.model.enumerations.Purpose;
import com.nikola.sneakershop.service.application.SneakerApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sneakers")
@CrossOrigin(origins = "http://localhost:3000")
public class SneakersController {
    private final SneakerApplicationService sneakerService;

    public SneakersController(SneakerApplicationService sneakerService) {
        this.sneakerService = sneakerService;
    }

    @GetMapping("/all")
    public List<DisplaySneakerListDto> getAllSneakers() {
        return this.sneakerService.listAll();
    }

    @GetMapping
    public Page<DisplaySneakerListDto> findAll(@RequestParam(required = false) String name,
                                               @RequestParam(required = false) List<Long> manufacturerIds,
                                               @RequestParam(required = false) List<Gender> genders,
                                               @RequestParam(required = false) List<Purpose> purposes,
                                               @RequestParam(required = false) List<Color> colors,
                                               @RequestParam(required = false) List<Integer> sizes,
                                               @RequestParam(required = false) Double minPrice,
                                               @RequestParam(required = false) Double maxPrice,
                                               Pageable pageable) {
        return this.sneakerService.findAll(name, manufacturerIds, genders, purposes, colors, sizes, minPrice, maxPrice, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplaySneakerDetailsDto> getSneaker(@PathVariable Long id) {
        return this.sneakerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplaySneakerDetailsDto> createSneaker(@RequestBody CreateSneakerDto sneaker) {
        return this.sneakerService.save(sneaker)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplaySneakerDetailsDto> updateSneaker(@PathVariable Long id,
                                                                  @RequestBody CreateSneakerDto sneaker) {
        return this.sneakerService.update(id, sneaker)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/updateSizes/{id}")
    public ResponseEntity<DisplaySneakerDetailsDto> updateSizes(@PathVariable Long id,
                                                                @RequestBody List<CreateSneakerSizeDto> sneakerSizes) {
        return this.sneakerService.updateSizes(id, sneakerSizes)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/sell/{id}")
    public ResponseEntity<DisplaySneakerDetailsDto> sellSneaker(@PathVariable Long id,
                                                                @RequestParam int size,
                                                                @RequestParam int quantity) {
        return this.sneakerService.sellSneaker(id, size, quantity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSneaker(@PathVariable Long id) {
        if (this.sneakerService.findById(id).isPresent()) {
            this.sneakerService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}

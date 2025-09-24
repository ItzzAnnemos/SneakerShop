package com.nikola.sneakershop.web;

import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.dto.CreateSneakerDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerDetailsDto;
import com.nikola.sneakershop.model.dto.DisplaySneakerListDto;
import com.nikola.sneakershop.model.dto.SneakerSizeDto;
import com.nikola.sneakershop.service.application.SneakerApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sneakers")
@CrossOrigin(origins = "http://localhost:3000")
public class SneakersController {
    private final SneakerApplicationService sneakerService;

    public SneakersController(SneakerApplicationService sneakerService) {
        this.sneakerService = sneakerService;
    }

    @GetMapping
    public List<DisplaySneakerListDto> getAllSneakers() {
        return this.sneakerService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplaySneakerDetailsDto> getSneaker(@PathVariable Long id) {
        return this.sneakerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filterSneakers(@RequestBody Map<String, Object> filters) {
        try {
            List<Sneaker> filteredSneakers = sneakerService.filterSneakers(filters);
            return ResponseEntity.ok(filteredSneakers);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred while filtering sneakers: " + e.getMessage()));
        }
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
                                                                @RequestBody List<SneakerSizeDto> sneakerSizes) {
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

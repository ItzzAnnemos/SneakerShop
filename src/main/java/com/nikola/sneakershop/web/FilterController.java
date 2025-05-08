package com.nikola.sneakershop.web;

import com.nikola.sneakershop.model.dto.EnumValueDto;
import com.nikola.sneakershop.model.enumerations.Color;
import com.nikola.sneakershop.model.enumerations.Gender;
import com.nikola.sneakershop.model.enumerations.Purpose;
import com.nikola.sneakershop.service.SneakerSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class FilterController {

    private final SneakerSizeService sneakerSizeService;

    @Autowired
    public FilterController(SneakerSizeService sneakerSizeService) {
        this.sneakerSizeService = sneakerSizeService;
    }

    @GetMapping("/sizes")
    public ResponseEntity<List<EnumValueDto>> getSizes() {
        return ResponseEntity.ok(sneakerSizeService.getAvailableSizes());
    }

    @GetMapping("/colors")
    public ResponseEntity<List<EnumValueDto>> getColors() {
        List<EnumValueDto> colors = Arrays.stream(Color.values())
                .map(color -> new EnumValueDto(color.getValue(), color.getLabel()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(colors);
    }

    @GetMapping("/genders")
    public ResponseEntity<List<EnumValueDto>> getGenders() {
        List<EnumValueDto> genders = Arrays.stream(Gender.values())
                .map(gender -> new EnumValueDto(gender.getValue(), gender.getLabel()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(genders);
    }

    @GetMapping("/purposes")
    public ResponseEntity<List<EnumValueDto>> getPurposes() {
        List<EnumValueDto> purposes = Arrays.stream(Purpose.values())
                .map(purpose -> new EnumValueDto(purpose.getValue(), purpose.getLabel()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(purposes);
    }
}

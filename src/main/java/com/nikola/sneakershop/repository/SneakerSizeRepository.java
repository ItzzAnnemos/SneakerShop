package com.nikola.sneakershop.repository;

import com.nikola.sneakershop.model.SneakerSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SneakerSizeRepository extends JpaRepository<SneakerSize, Long> {

     Optional<SneakerSize> findBySneakerIdAndSize(Long id, int size);

     @Query("SELECT DISTINCT s.size FROM SneakerSize s WHERE s.quantity > 0 ORDER BY s.size")
     List<Integer> findAllDistinctBySize();
}

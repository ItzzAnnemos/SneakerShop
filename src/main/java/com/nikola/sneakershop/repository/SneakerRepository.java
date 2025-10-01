package com.nikola.sneakershop.repository;

import com.nikola.sneakershop.model.Sneaker;
import org.springframework.stereotype.Repository;

@Repository
public interface SneakerRepository extends JpaSpecificationRepository<Sneaker, Long> {

}

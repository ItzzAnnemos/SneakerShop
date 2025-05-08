package com.nikola.sneakershop.repository;

import com.nikola.sneakershop.model.Manufacturer;
import com.nikola.sneakershop.model.Sneaker;
import com.nikola.sneakershop.model.enumerations.Color;
import com.nikola.sneakershop.model.enumerations.Gender;
import com.nikola.sneakershop.model.enumerations.Purpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SneakerRepository extends JpaRepository<Sneaker, Long> {
    @Query("SELECT DISTINCT s FROM Sneaker s JOIN FETCH s.sizes sz " +
            "WHERE (:query IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', CAST(:query AS string), '%'))) " +
            "AND (:#{#manufacturers == null} = true OR s.manufacturer.name IN :manufacturers) " +
            "AND (:#{#colors == null} = true OR s.color IN :colors) " +
            "AND (:#{#purposes == null} = true OR s.purpose IN :purposes) " +
            "AND (:#{#genders == null} = true OR s.gender IN :genders) " +
            "AND (:#{#sizes == null} = true OR sz.size IN :sizes) " +
            "AND (:minPrice IS NULL OR s.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR s.price <= :maxPrice) " +
            "AND sz.quantity > 0")
    List<Sneaker> findByCriteria(@Param("query") String query,
                                 @Param("manufacturers") List<String> manufacturers,
                                 @Param("colors") List<Color> colors,
                                 @Param("purposes") List<Purpose> purposes,
                                 @Param("genders") List<Gender> genders,
                                 @Param("sizes") List<Integer> sizes,
                                 @Param("minPrice") Double minPrice,
                                 @Param("maxPrice") Double maxPrice);
}

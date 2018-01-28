package ch.fhnw.edu.rental.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.edu.rental.model.PriceCategory;

public interface PriceCategoryRepository extends JpaRepository<PriceCategory, Long> {
}

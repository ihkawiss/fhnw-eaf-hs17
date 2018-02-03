package ch.fhnw.edu.eaf.moviemgmt.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.edu.eaf.moviemgmt.model.PriceCategory;

public interface PriceCategoryRepository extends JpaRepository<PriceCategory, Long> {
}

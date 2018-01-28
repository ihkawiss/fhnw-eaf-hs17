package ch.fhnw.edu.rental.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;

public interface RentalRepository extends JpaRepository<Rental, Long> {
	List<Rental> findByUser(User user);
}

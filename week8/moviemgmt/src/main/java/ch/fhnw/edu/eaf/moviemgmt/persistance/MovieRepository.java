package ch.fhnw.edu.eaf.moviemgmt.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.edu.eaf.moviemgmt.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	List<Movie> findByTitle(String title);
}

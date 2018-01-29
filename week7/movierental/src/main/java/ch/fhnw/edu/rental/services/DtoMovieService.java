package ch.fhnw.edu.rental.services;

import java.util.List;

import ch.fhnw.edu.rental.model.PriceCategory;
import ch.fhnw.edu.rental.services.dto.MovieDto;

public interface DtoMovieService {
	List<MovieDto> getAllMovies();
	MovieDto getMovieById(Long id);
	Long saveOrUpdateMovie(MovieDto movie);
	void deleteMovie(Long id);
	List<PriceCategory> getAllPriceCategories();
}

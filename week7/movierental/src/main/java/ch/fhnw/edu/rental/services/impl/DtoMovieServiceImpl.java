package ch.fhnw.edu.rental.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.model.PriceCategory;
import ch.fhnw.edu.rental.services.DtoMovieService;
import ch.fhnw.edu.rental.services.MovieService;
import ch.fhnw.edu.rental.services.dto.MovieDto;

@Service
@Transactional
public class DtoMovieServiceImpl implements DtoMovieService {

	@Autowired
	private MovieService movieService;

	@Override
	public List<MovieDto> getAllMovies() {
		List<MovieDto> dtoList = new ArrayList<>();
		for(Movie m : movieService.getAllMovies()) {
			dtoList.add(convertMovie(m));
		}
		
		return dtoList;
	}

	@Override
	public MovieDto getMovieById(Long id) {
		return convertMovie(movieService.getMovieById(id));
	}

	@Override
	public Long saveOrUpdateMovie(MovieDto movie) {
		Movie m = new Movie(movie.getTitle(), movie.getReleaseDate(), movie.getPriceCategory());
		m.setRented(movie.isRented());
		m.setId(movie.getId());
		
		return movieService.saveMovie(m).getId();
	}

	@Override
	public void deleteMovie(Long id) {
		Movie m = movieService.getMovieById(id);
		movieService.deleteMovie(m);
	}

	private MovieDto convertMovie(Movie m) {
		return new MovieDto(m.getId(), m.getTitle(), m.getReleaseDate(), m.isRented(), m.getPriceCategory());
	}

	@Override
	public List<PriceCategory> getAllPriceCategories() {
		return movieService.getAllPriceCategories();
	}

}

package ch.fhnw.edu.rental.persistence.jdbc;

import java.util.List;

import org.springframework.stereotype.Component;

import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.persistence.MovieRepository;

@Component
public class JdbcMovieRepository implements MovieRepository {

	@Override
	public Movie findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie save(Movie t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Movie entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Movie> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

}

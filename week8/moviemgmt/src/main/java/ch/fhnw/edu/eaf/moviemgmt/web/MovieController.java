package ch.fhnw.edu.eaf.moviemgmt.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.edu.eaf.moviemgmt.model.Movie;
import ch.fhnw.edu.eaf.moviemgmt.model.PriceCategory;
import ch.fhnw.edu.eaf.moviemgmt.persistance.MovieRepository;
import ch.fhnw.edu.eaf.moviemgmt.persistance.PriceCategoryRepository;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private static final Logger log = Logger.getLogger(MovieController.class);

	@Autowired
	private MovieRepository movieRepo;

	@Autowired
	private PriceCategoryRepository priceCategoryRepo;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findAll() {
		List<Movie> movies = movieRepo.findAll();
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}

	@RequestMapping(value = "priceCategories", method = RequestMethod.GET)
	public ResponseEntity<List<PriceCategory>> findAllPriceCategories() {
		List<PriceCategory> categories = priceCategoryRepo.findAll();
		return new ResponseEntity<List<PriceCategory>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Movie> findById(@PathVariable Long id) {
		Movie movie = movieRepo.findOne(id);
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Movie> create(@PathVariable Movie m) {
		Movie movie = movieRepo.save(m);
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Movie> update(@PathVariable Movie m) {
		Movie movie = movieRepo.save(m);
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		movieRepo.delete(id);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}

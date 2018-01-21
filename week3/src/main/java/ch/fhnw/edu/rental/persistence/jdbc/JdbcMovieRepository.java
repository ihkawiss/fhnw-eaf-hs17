package ch.fhnw.edu.rental.persistence.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.persistence.MovieRepository;

@Component
public class JdbcMovieRepository implements MovieRepository {

	private final Logger log = LoggerFactory.getLogger(JdbcMovieRepository.class);
	
	@Autowired
	private JdbcTemplate template;

	@Autowired
	private JdbcPriceCategoryRepository priceCategoryRepo;

	@Override
	public Movie findOne(Long id) {
		Movie m = template.queryForObject(
                "SELECT * FROM Movies WHERE MOVIE_ID = ?",
                (rs, rowNum) -> createMovie(rs),
                id
        );
		
		return m;
	}

	@Override
	public List<Movie> findAll() {
		return template.query(
				"select * from Movies", 
				(rs, row) -> createMovie(rs)
		);
	}

	@Override
	public Movie save(Movie t) {
		
		if(exists(t.getId())) {
			
			template.update(
                    "UPDATE Movies SET MOVIE_TITLE=?, MOVIE_RELEASEDATE=?, MOVIE_RENTED=?, PRICECATEGORY_FK=? where MOVIE_ID=?",
                    t.getTitle(),
                    t.getReleaseDate(),
                    t.isRented(),
                    t.getPriceCategory().getId(),
                    t.getId()
            );
			
		} else {

			template.update(
                    "insert into movies (MOVIE_TITLE, MOVIE_RELEASEDATE, MOVIE_RENTED, PRICECATEGORY_FK) VALUES (?, ?, ?, ?)",
                    t.getTitle(),
                    t.getReleaseDate(),
                    t.isRented(),
                    t.getPriceCategory().getId()
            );
			
			// find the inserted movie based on title and max id
            return findByTitle(t.getTitle()).stream().max(Comparator.comparing(m -> m.getId())).get();
		}
		
		return t;
	}

	@Override
	public void delete(Long id) {
		
		int rowsUpdated = template.update("delete from Movies where MOVIE_ID = ?", new Object[] {id});

		if(rowsUpdated > 0) {
			log.info("Movie with ID " + id + " was deleted.");
		} else {
			log.info("No movie with ID" + id + "found, nothing deleted.");
		}
	}

	@Override
	public void delete(Movie entity) {
		if(entity == null)
			throw new IllegalArgumentException("movie can't be null");
		
		delete(entity.getId());
	}

	@Override
	public boolean exists(Long id) {
		
		if(id == null)
			return false;
		
		Long rowCount = template.queryForObject(
                "SELECT COUNT(MOVIE_ID) FROM Movies WHERE MOVIE_ID = ?",
                Long.class,
                id
        );

        return rowCount == 1;
	}

	@Override
	public long count() {
		return findAll().size();
	}

	@Override
	public List<Movie> findByTitle(String title) {
		return template.query("select * from MOVIES where MOVIE_TITLE = ?", (rs, row) -> createMovie(rs), title);
	}

	// helper to create instance of movie by a ResultSet
    private Movie createMovie(ResultSet rs) throws SQLException {

        long priceCategory = rs.getLong("PRICECATEGORY_FK");
        Movie tmp =  new Movie(
                rs.getString("MOVIE_TITLE"),
                rs.getDate("MOVIE_RELEASEDATE"),
                priceCategoryRepo.findOne(priceCategory)
        );

        tmp.setId(rs.getLong("MOVIE_ID"));
        tmp.setRented( rs.getBoolean("MOVIE_RENTED"));

        return tmp;
    }

}

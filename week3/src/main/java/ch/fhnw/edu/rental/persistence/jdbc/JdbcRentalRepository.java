package ch.fhnw.edu.rental.persistence.jdbc;

import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.persistence.MovieRepository;
import ch.fhnw.edu.rental.persistence.RentalRepository;
import ch.fhnw.edu.rental.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Component
public class JdbcRentalRepository implements RentalRepository{

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MovieRepository movieRepo;

    @Override
    public List<Rental> findByUser(User user) {

        List<Rental> r = template.query(
                "select * from rentals where USER_ID=?",
                (rs, rowNum) -> createRental(rs),
                user.getId()
        );

        return r;
    }

    @Override
    public Rental findOne(Long id) {

        return template.query(
                "select * from rentals where RENTAL_ID=?",
                (rs, rowNum) -> createRental(rs),
                id
        ).get(0);

    }

    @Override
    public List<Rental> findAll() {

        return template.query(
                "select * from rentals",
                (rs, rowNum) -> createRental(rs)
        );

    }

    @Override
    public Rental save(Rental rental) {

        if(exists(rental.getId())){

            template.update(
                    "UPDATE Rentals SET RENTAL_RENTALDATE=?, RENTAL_RENTALDAYS=?, USER_ID=?, MOVIE_ID=? where RENTAL_ID=?",
                    rental.getRentalDate(),
                    rental.getRentalDays(),
                    rental.getUser().getId(),
                    rental.getMovie().getId(),
                    rental.getId()
            );

        } else {

            template.update(
                    "insert into Rentals (RENTAL_RENTALDATE, RENTAL_RENTALDAYS, USER_ID, MOVIE_ID) VALUES (?, ?, ?, ?)",
                    rental.getRentalDate(),
                    rental.getRentalDays(),
                    rental.getUser().getId(),
                    rental.getMovie().getId()
            );

            // find the inserted movie based on title and max id
            Rental newRental = findAll().stream().max(Comparator.comparing(m -> m.getId())).get();

        }

        return rental;

    }

    @Override
    public void delete(Long id) {

        template.update(
                "DELETE FROM Rentals WHERE RENTAL_ID = ?",
                id
        );
    }

    @Override
    public void delete(Rental rental) {
        delete(rental.getId());
    }

    @Override
    public boolean exists(Long id) {

        Long rowCount = template.queryForObject(
                "SELECT COUNT(RENTAL_ID) FROM Rentals WHERE RENTAL_ID = ?",
                Long.class,
                id
        );

        return rowCount == 1;

    }

    @Override
    public long count() {

        return template.queryForObject(
                "SELECT COUNT(RENTAL_ID) FROM Rentals",
                Long.class
        );

    }

    // helper to create instance of movie by a ResultSet
    private Rental createRental(ResultSet rs) throws SQLException {

        User user = userRepo.findOne(rs.getLong("USER_ID"));
        Movie movie = movieRepo.findOne(rs.getLong("MOVIE_ID"));

        return new Rental(user, movie, rs.getInt("RENTAL_RENTALDAYS"));
    }
}

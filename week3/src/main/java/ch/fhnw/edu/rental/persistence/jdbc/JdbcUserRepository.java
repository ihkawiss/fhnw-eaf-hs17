package ch.fhnw.edu.rental.persistence.jdbc;

import ch.fhnw.edu.rental.model.User;
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
public class JdbcUserRepository implements UserRepository{

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private RentalRepository rentalRepo;

    @Override
    public List<User> findByLastName(String lastName) {

        return template.query(
                "select * from users where user_name = ?",
                (rs, rowNum) -> createUser(rs),
                lastName
        );

    }

    @Override
    public List<User> findByFirstName(String firstName) {

        return template.query(
                "select * from users where user_firstname = ?",
                (rs, rowNum) -> createUser(rs),
                firstName
        );

    }

    @Override
    public List<User> findByEmail(String email) {

        return template.query(
                "select * from users where user_firstname = ?",
                (rs, rowNum) -> createUser(rs),
                email
        );

    }

    @Override
    public User findOne(Long id) {

        return template.query(
                "select * from users where USER_ID = ?",
                (rs, rowNum) -> createUser(rs),
                id
        ).get(0);

    }

    @Override
    public List<User> findAll() {

        return template.query(
                "select * from users",
                (rs, rowNum) -> createUser(rs)
        );

    }

    @Override
    public User save(User user) {

        if(exists(user.getId())){

            template.update(
                    "UPDATE Users SET USER_EMAIL=?, USER_FIRSTNAME=?, USER_NAME=? where USER_ID=?",
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getId()
            );

        } else {

            template.update(
                    "insert into Users (USER_EMAIL, USER_FIRSTNAME, USER_NAME) VALUES (?, ?, ?)",
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName()
            );

            // find the inserted movie based on title and max id
            User newUser = findAll().stream().max(Comparator.comparing(m -> m.getId())).get();

            user.setId(newUser.getId());
        }

        return user;

    }

    @Override
    public void delete(Long id) {

        template.update(
                "DELETE FROM Users WHERE USER_ID = ?",
                id
        );

    }

    @Override
    public void delete(User user) {
        delete(user.getId());
    }

    @Override
    public boolean exists(Long id) {

        Long rowCount = template.queryForObject(
                "SELECT COUNT(USER_ID) FROM Users WHERE USER_ID = ?",
                Long.class,
                id
        );

        return rowCount == 1;

    }

    @Override
    public long count() {

        return template.queryForObject(
                "SELECT COUNT(USER_ID) FROM Users",
                Long.class
        );

    }

    // helper to create instance of user by a ResultSet
    private User createUser(ResultSet rs) throws SQLException {

        User user = new User(
                rs.getString("USER_NAME"),
                rs.getString("USER_FIRSTNAME")
        );

        // further data
        user.setId(rs.getLong("USER_ID"));
        user.setEmail(rs.getString("USER_EMAIL"));

        return user;
    }
}

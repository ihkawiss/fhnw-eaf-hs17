package ch.fhnw.edu.jpa.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

import ch.fhnw.edu.jpa.model.Movie;
import ch.fhnw.edu.jpa.model.User;

@SpringBootApplication
@EntityScan(basePackageClasses = Movie.class)
public class Test1 implements CommandLineRunner {

	@PersistenceContext
	EntityManager em;

	public static void main(String[] args) {
		new SpringApplicationBuilder(Test1.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		test1();
		System.out.println("done");
	}

	private void test1() {
		Movie movie = em.find(Movie.class, 1L);
		System.out.println(movie);
		if (movie != null)
			System.out.println(movie.getTitle());

		TypedQuery<User> query = em.createQuery("SELECT u from User u inner join u.rentals r where r.movie.title = :movie ", User.class);
		query.setParameter("movie", movie.getTitle());

		List<User> users = query.getResultList();
		if (users.size() == 0) {
			System.out.println("nicht ausgeliehen");
		} else {
			System.out.println("ausgeliehen an " + users.get(0).getEmail());
		}

	}

}

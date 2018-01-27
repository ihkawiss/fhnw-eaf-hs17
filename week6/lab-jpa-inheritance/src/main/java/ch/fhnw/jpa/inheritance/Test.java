package ch.fhnw.jpa.inheritance;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Test implements CommandLineRunner {

	@PersistenceContext
	EntityManager em;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Test.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		test1();
	}
	
	private void test1() {
		Post p = new Post("First Post", "Dominik", "Sample content");
		Announcement a = new Announcement("First Announcement", "Dominik", new Date());
		em.persist(p);
		em.persist(a);
	}
	
}

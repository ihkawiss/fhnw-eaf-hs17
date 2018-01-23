package ch.fhnw.edu.jpa.test5;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

import ch.fhnw.edu.jpa.model.Address;
import ch.fhnw.edu.jpa.model.Customer;

@SpringBootApplication
@EntityScan(basePackageClasses=Customer.class)
public class Test5 implements CommandLineRunner {

	@PersistenceContext
	EntityManager em;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Test5.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		
		Customer c = new Customer("Gosling", 55);
		Address a = new Address("Infinite Loop 1", "Cupertino");
		c.setAddress(a);
		
		// em.persist(a);
		em.persist(c);
		
		System.out.println("done");
	}
}

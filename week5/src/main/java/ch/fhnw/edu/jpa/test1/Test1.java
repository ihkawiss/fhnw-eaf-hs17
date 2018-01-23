package ch.fhnw.edu.jpa.test1;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

import ch.fhnw.edu.jpa.model.Customer;

@SpringBootApplication
@EntityScan(basePackageClasses=Customer.class)
public class Test1 implements CommandLineRunner {
	
	@PersistenceContext
	EntityManager em;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Test1.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Customer c;
		c = new Customer("Meier", 24);
		em.persist(c);
		System.out.println(c.getId());
		
		c = new Customer("Mueller", 24);
		em.persist(c);
		System.out.println(c.getId());

		System.out.println("done");
	}

}

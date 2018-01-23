package ch.fhnw.edu.jpa.test4;

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
public class Test4 implements CommandLineRunner {

	@PersistenceContext
	EntityManager em;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Test4.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.out.println("done");
		System.out.println("inspect now the generated schema using the h2 console");
	}
}

package ch.fhnw.edu.jpa.test6;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

import ch.fhnw.edu.jpa.model.Customer;
import ch.fhnw.edu.jpa.model.Order;

@SpringBootApplication
@EntityScan(basePackageClasses=Customer.class)
public class Test6 implements CommandLineRunner {

	@PersistenceContext
	EntityManager em;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Test6.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Customer c1 = new Customer("Haller", 52);
		Customer c2 = new Customer("Schneider", 66);

		Order o1 = new Order();
		Order o2 = new Order();
		Order o3 = new Order();

		c1.addOrder(o1);
		c1.addOrder(o2);

//		c2.addOrder(o1);
		c2.addOrder(o3);

		em.persist(c1);
		em.persist(c2);

		System.out.println("done");
	}

}

package ch.fhnw.edu.jpa.test7;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

import ch.fhnw.edu.jpa.model.Customer;

@SpringBootApplication
@EntityScan(basePackageClasses=Customer.class)
public class Test7 implements CommandLineRunner {

	@PersistenceContext
	EntityManager em;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Test7.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.out.println("Default> " + em.getFlushMode());
		em.setFlushMode(FlushModeType.COMMIT);
		System.out.println("Current> " + em.getFlushMode());
		
		Customer c = em.find(Customer.class, 1);
		c.getAddress().setCity("Basel");

//		em.flush();
		
		TypedQuery<String> q = em.createQuery("select a.city from Address a", String.class);
		List<String> cities = q.getResultList();
		for (String city : cities) {
			System.out.println(city);
		}
		
		System.out.println("done");
		System.exit(0);
	}
}

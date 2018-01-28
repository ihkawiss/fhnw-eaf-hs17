package ch.fhnw.eaf;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import ch.fhnw.eaf.mappers.MovieMapper;
import ch.fhnw.eaf.rental.dto.RentalDto;
import ch.fhnw.eaf.rental.dto.UserDto;
import ch.fhnw.eaf.rental.model.Rental;
import ch.fhnw.eaf.rental.model.User;

@SpringBootApplication
@Transactional
public class Main implements CommandLineRunner {
	
	@Autowired
	MovieMapper mapper;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Main.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		test1();
		test2();
	}

	private void test1() {
		User user = new User();
		user.setId(1234L);
		user.setLastName("Mueller");
		user.setFirstName("Peter");

		Rental rental = new Rental(444L, 14);
		rental.setUser(user);
		user.getRentals().add(rental);

		UserDto dto = mapper.userToUserDto(user);
		System.out.println(dto);
	}

	private void test2() {
		User user = new User();
		user.setId(1234L);
		user.setLastName("Mueller");
		user.setFirstName("Peter");

		Rental rental = new Rental(555L, 15);
		rental.setUser(user);
		user.getRentals().add(rental);

		RentalDto dto = mapper.rentalToRentalDto(rental);
		System.out.println(dto);
	}


}
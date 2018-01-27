package ch.fhnw.edu.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	private String lastName;
	
	private String firstName;
	
	private String email;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Rental> rentals = new ArrayList<>();
	
	User() { 
		// JPA requires that a default constructor is available
	}
	
	public User(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public Long getId() {
		return id;
	}


	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

}

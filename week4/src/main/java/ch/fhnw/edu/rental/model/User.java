package ch.fhnw.edu.rental.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@NamedQueries({
	@NamedQuery(name = "User.all", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.count", query = "SELECT COUNT(u) FROM User u"),
	@NamedQuery(name = "User.byLastname", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
	@NamedQuery(name = "User.byFirstname", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
	@NamedQuery(name = "User.byMail", query = "SELECT u FROM User u WHERE u.email = :email")
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "USER_NAME")
	private String lastName;

	@Column(name = "USER_FIRSTNAME")
	private String firstName;

	@Column(name = "USER_EMAIL")
	private String email;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private List<Rental> rentals;

	public User() {
	}

	public User(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.rentals = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public int getCharge() {
		int result = 0;
		for (Rental rental : rentals) {
			result += rental.getMovie().getPriceCategory().getCharge(rental.getRentalDays());
		}
		return result;
	}

}

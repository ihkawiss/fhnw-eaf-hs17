package ch.fhnw.eaf.rental.dto;

import java.util.HashSet;
import java.util.Set;

public class UserDto {
	private long id;
	private String lastName;
	private String firstName;
	private Set<Long> rentalIds = new HashSet<>();

	public Set<Long> getRentalIds() {
		return rentalIds;
	}

	public void setRentalIds(Set<Long> rentalIds) {
		this.rentalIds = rentalIds;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String toString() {
		return String.format("UserDTO(%s, %s, %s, %s)", id, lastName, firstName, rentalIds);
	}
}

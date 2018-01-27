package ch.fhnw.edu.jpa.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movie {
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	
	private boolean rented;
	
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	protected Movie() { 
		// JPA requires that a default constructor is available
	}

	public Movie(String title, Date releaseDate) throws NullPointerException {
		if ((title == null) || (releaseDate == null)) {
			throw new IllegalArgumentException("not all input parameters are set!");
		}
		this.title = title;
		this.releaseDate = releaseDate;
		this.rented = false;
	}

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

}

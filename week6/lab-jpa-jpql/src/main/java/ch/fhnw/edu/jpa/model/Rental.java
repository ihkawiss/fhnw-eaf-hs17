package ch.fhnw.edu.jpa.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rental {
	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private Movie movie;
	
	@ManyToOne
	private User user;
	
	@Temporal(TemporalType.DATE)
	private Date rentalDate;
	
	private int rentalDays;
	
	protected Rental() { 
		// JPA requires that a default constructor is available
	}
	
	public Rental(User user, Movie movie, int rentalDays) {
		this(user, movie, rentalDays, Calendar.getInstance().getTime());
	}
	
	public Rental(User user, Movie movie, int rentalDays, Date rentalDate) {
		if (user == null || movie == null || rentalDays <= 0) {
			throw new IllegalArgumentException("not all input parameters are set!");
		}
		this.user = user;
		this.movie = movie;
		this.rentalDays = rentalDays;
		this.rentalDate = rentalDate;
	}
	
	public Long getId() {
		return id;
	}

	public Movie getMovie() {
		return movie;
	}

	public User getUser() {
		return user;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public int getRentalDays() {
		return rentalDays;
	}

}

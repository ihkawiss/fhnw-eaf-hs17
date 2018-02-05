package ch.fhnw.edu.eaf.rentalmgmt.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rentals")
public class Rental {
	@Id
	@GeneratedValue
	@Column(name = "rental_id")
	private Long id;

	@Column(name = "movie_id", nullable = false)
	private Long movieId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "rental_rentaldate")
	private Date rentalDate;

	@Column(name = "rental_rentaldays")
	private int rentalDays;

	protected Rental() {
		this.rentalDate = Calendar.getInstance().getTime();
	}

	public Rental(Long userId, Long movieId, int rentalDays) {
		if ((userId == null) || (movieId == null) || (rentalDays <= 0)) {
			throw new NullPointerException("not all input parameters are set!");
		}
		// if (movie.isRented()) {
		// throw new IllegalStateException("movie is already rented!");
		// }
		this.userId = userId;
		// user.getRentals().add(this);
		this.movieId = movieId;
		this.rentalDays = rentalDays;
		this.rentalDate = Calendar.getInstance().getTime();
	}

	public int calcRemainingDaysOfRental(Date date) {
		if (date == null) {
			throw new NullPointerException("given date is not set!");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(rentalDate);
		calendar.add(Calendar.DAY_OF_YEAR, rentalDays);
		int endDay = calendar.get(Calendar.DAY_OF_YEAR);
		int endYear = calendar.get(Calendar.YEAR);
		calendar.setTime(date);
		int max = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		int actDay = calendar.get(Calendar.DAY_OF_YEAR);
		int actYear = calendar.get(Calendar.YEAR);
		int diffDay = endDay - actDay;
		if (max != 365) {
			return 366 * (endYear - actYear) + diffDay;
		}
		else {
			return 365 * (endYear - actYear) + diffDay;
		}
	}

	// public double getRentalFee() {
	// return movie.getPriceCategory().getCharge(rentalDays);
	// }

	public Long getId() {
		return id;
	}

	public Long getMovieId() {
		return movieId;
	}

	public Long getUserId() {
		return userId;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}
}

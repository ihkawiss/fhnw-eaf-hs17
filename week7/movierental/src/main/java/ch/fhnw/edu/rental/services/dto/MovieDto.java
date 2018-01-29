package ch.fhnw.edu.rental.services.dto;

import java.util.Date;

import ch.fhnw.edu.rental.model.PriceCategory;

public class MovieDto {

	private Long id;
	private String title;
	private Date releaseDate;
	private boolean rented;
	private PriceCategory priceCategory;

	public MovieDto(Long id, String title, Date releaseDate, boolean rented, PriceCategory priceCategory) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.rented = rented;
		this.priceCategory = priceCategory;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public PriceCategory getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}


}

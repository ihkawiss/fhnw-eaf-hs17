package ch.fhnw.edu.eaf.moviemgmt.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NewRelease")
public class PriceCategoryNewRelease extends PriceCategory {

	public PriceCategoryNewRelease() {
	}
	
	@Override
	public double getCharge(int daysRented) {
		return daysRented * 3;
	}

	@Override
	public int getFrequentRenterPoints(int daysRented) {
		// add bonus for two day new release rental
		if (daysRented > 1) {
			return 2;
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return "New Release";
	}
}

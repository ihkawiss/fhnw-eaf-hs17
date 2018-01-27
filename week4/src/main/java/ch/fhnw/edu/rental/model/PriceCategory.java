package ch.fhnw.edu.rental.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PRICECATEGORIES")
@DiscriminatorColumn(name = "PRICECATEGORY_TYPE")
@NamedQueries({
	@NamedQuery(name = "PriceCategory.all", query = "SELECT pc FROM PriceCategory pc"),
	@NamedQuery(name = "PriceCategory.count", query = "SELECT COUNT(pc) FROM PriceCategory pc")
})
public abstract class PriceCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRICECATEGORY_ID")
	private Long id;

	public PriceCategory() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public abstract double getCharge(int daysRented);

	public int getFrequentRenterPoints(int daysRented) {
		return 1;
	}
}

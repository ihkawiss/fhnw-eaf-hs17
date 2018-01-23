package ch.fhnw.edu.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {

	public Order() {
	}

	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	public Customer customer;
	
	@Id
	@GeneratedValue
	private int id;

	public int getId() {
		return id;
	}
}

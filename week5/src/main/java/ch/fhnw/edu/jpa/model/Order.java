package ch.fhnw.edu.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {

	public Order() {
	}

	@Id
	@GeneratedValue
	private int id;

	public int getId() {
		return id;
	}
}

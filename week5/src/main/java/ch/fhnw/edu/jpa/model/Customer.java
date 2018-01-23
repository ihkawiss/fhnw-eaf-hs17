package ch.fhnw.edu.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	@OneToOne
	private Address address;

	@OneToMany(mappedBy = "customer")
	private List<Order> orders = new ArrayList<Order>();
	
	private int age;

	protected Customer() {
	}

	public Customer(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Order> getOrders(){
		return orders;
	}
	
	@Override
	public String toString() {
		if (address == null)
			return String.format("%-10s [%d]", name, age);
		else
			return String.format("%-10s [%d], %s, %s", name, age, address.getStreet(), address.getCity());
	}

	public void addOrder(Order order) {
	}

}

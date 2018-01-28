package ch.fhnw.eaf.rental.model;

public class Rental {
	private Long id;
	private int days;
	private User user;

	public Rental(Long id, int days) {
		this.id = id;
		this.days = days;
	}
	
	public Rental() {
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

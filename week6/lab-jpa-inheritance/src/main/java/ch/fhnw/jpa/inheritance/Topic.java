package ch.fhnw.jpa.inheritance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Topic {
	@Id @GeneratedValue
	private Long id;
	private String title;
	private String owner;
	
	protected Topic() { }
	
	public Topic(String title, String owner) {
		this.title = title;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getOwner() {
		return owner;
	}
	
}

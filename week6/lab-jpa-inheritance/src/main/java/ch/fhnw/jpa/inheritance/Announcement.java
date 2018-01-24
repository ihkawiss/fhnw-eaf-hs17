package ch.fhnw.jpa.inheritance;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Announcement extends Topic {
	@Temporal(TemporalType.DATE)
	private Date validUntil;
	
	protected Announcement() {}
	
	public Announcement(String title, String owner) {
		this(title, owner, null);
	}

	public Announcement(String title, String owner, Date validUntil) {
		super(title, owner);
		this.validUntil = validUntil;
	}

}

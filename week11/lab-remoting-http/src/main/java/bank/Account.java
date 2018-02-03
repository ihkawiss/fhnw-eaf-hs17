package bank;

import java.io.Serializable;

public class Account implements Serializable {
	private static final long serialVersionUID = -8019327853252924820L;
	
	private String name;

	public Account(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

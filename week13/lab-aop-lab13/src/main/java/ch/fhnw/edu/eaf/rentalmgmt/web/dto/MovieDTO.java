package ch.fhnw.edu.eaf.rentalmgmt.web.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class MovieDTO {
	@JsonProperty("id")
	private Long id;

	public void setId(Long id) {
		this.id = id;
	}
}


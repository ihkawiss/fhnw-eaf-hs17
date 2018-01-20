package ch.fhnw.edu.eaf.app.domain.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ch.fhnw.edu.eaf.app.domain.MessageProvider;

@Component
public class ExternalizedHelloWorldMessageProvider implements MessageProvider {

	@Value("${helloworld.message}")
	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}

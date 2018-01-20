package ch.fhnw.edu.eaf.app.domain.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.fhnw.edu.eaf.app.domain.MessageProvider;
import ch.fhnw.edu.eaf.app.domain.MessageRenderer;

@Component
public class StandardOutRenderer implements MessageRenderer {

	@Autowired // injects MessageProvider bean
	private MessageProvider messageProvider;

	@Override
	public void setMessageProvider(MessageProvider mp) {
		this.messageProvider = mp;
	}

	@Override
	public MessageProvider getMessageProvider() {
		return messageProvider;
	}

	@Override
	public void render() {
		System.out.println(messageProvider.getMessage());
		;
	}

}

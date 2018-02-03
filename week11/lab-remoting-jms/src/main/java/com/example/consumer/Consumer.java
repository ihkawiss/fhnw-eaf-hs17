package com.example.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Consumer implements MessageListener {
	private Logger log = LoggerFactory.getLogger(Consumer.class);

	@Override
	public void onMessage(Message message) {
		try {
			log.info("Consumer> " + message.getBody(Object.class));
		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}
}
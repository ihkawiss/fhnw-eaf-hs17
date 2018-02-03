package com.example.consumer;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@SpringBootApplication
public class ConsumerApp {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApp.class, args);
	}

	@Autowired
	Consumer consumer;

	@Value("${queue.demo}")
	private String queue;

	@Bean
	public DefaultMessageListenerContainer messageListener(ConnectionFactory connectionFactory) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setDestinationName(queue);
		container.setMessageListener(consumer);
		return container;
	}
}
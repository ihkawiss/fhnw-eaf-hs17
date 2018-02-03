package com.example.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class ProducerApp {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApp.class, args);
	}

	@Value("${queue.demo}")
	String queue;

	@Bean
	CommandLineRunner sendMessage(JmsTemplate jmsTemplate) {
		return args -> {
			jmsTemplate.convertAndSend(queue, "Spring Boot Rocks! " + System.currentTimeMillis());
			jmsTemplate.convertAndSend(queue, "Spring Boot Rocks! " + System.currentTimeMillis());
			jmsTemplate.convertAndSend(queue, "Spring Boot Rocks! " + System.currentTimeMillis());
			jmsTemplate.convertAndSend(queue, "Spring Boot Rocks! " + System.currentTimeMillis());
		};
	}
}
package ch.fhnw.edu.eaf.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ch.fhnw.edu.eaf.app.domain.MessageRenderer;

@SpringBootApplication
public class SpringHelloWorldApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringHelloWorldApplication.class, args);
		MessageRenderer renderer = (MessageRenderer) context.getBean(MessageRenderer.class);
		renderer.render();
	}
}

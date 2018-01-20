package ch.fhnw.edu.eaf.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ch.fhnw.edu.eaf.app")
@PropertySource("classpath:application.properties")
public class MyContextConfiguration {
	
}

package server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import bank.AccountService;

@SpringBootApplication
public class Server {

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

	@Value("${serviceName}")
	String serviceName;

	@Bean
	public RmiServiceExporter getExporter(AccountService service) {
		RmiServiceExporter exporter = new RmiServiceExporter();
		exporter.setServiceName(serviceName);
		exporter.setService(service);
		exporter.setServiceInterface(AccountService.class);
		return exporter;
	}

}

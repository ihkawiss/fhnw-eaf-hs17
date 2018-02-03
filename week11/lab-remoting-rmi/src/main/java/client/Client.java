package client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import bank.AccountService;

@SpringBootApplication
public class Client {

	@Value("${rmi.host}")
	String host;

	@Value("${rmi.port}")
	int port;

	@Value("${serviceName}")
	String serviceName;

	public static void main(String[] args) {
		SpringApplication.run(Client.class, args);
	}

	@Bean
	CommandLineRunner run(AccountService service) {
		return args -> {
			System.out.println("Client started");
			
			Object proxy = service;
			System.out.println("proxy.getClass() = " + proxy.getClass().getCanonicalName());
			System.out.println("implemented interfaces:");
			for (Class<?> c : proxy.getClass().getInterfaces()) {
				System.out.println("> " + c.getCanonicalName());
			}
		};
	}

	@Bean
	AccountService getProxy() {
		RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
		String url = String.format("rmi://%s:%s/%s", host, port, serviceName);
		proxy.setServiceUrl(url);
		proxy.setServiceInterface(AccountService.class);
		proxy.afterPropertiesSet();
		return (AccountService)proxy.getObject();
	}

}

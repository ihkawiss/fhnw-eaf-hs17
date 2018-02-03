package client;

import java.rmi.Naming;
import java.rmi.Remote;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import bank.Account;
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
			
			// Aufgabe 2
			for(int i = 0; i < 10; i++) {
				service.insertAccount(new Account("Private"));
			}
		
			if(!service.getAccounts("Private").isEmpty()) {
				if(service.getAccounts("Private").size() % 10 == 0)
					System.out.println("Account was created via RMI!");
				else
					System.err.println("Count of created accounts wrong!" + service.getAccounts("Private").size());
			} else {
				System.err.print("RMI creation failed!");
			}
			
			
			// Aufgabe 3
			proxy = Naming.lookup("rmi://localhost:1099/AccountService");
			System.out.println("proxy.getClass() = " + proxy.getClass().getCanonicalName());
			System.out.println(String.format("Castable to AccountService?  %s!", proxy instanceof AccountService)); 			
			
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

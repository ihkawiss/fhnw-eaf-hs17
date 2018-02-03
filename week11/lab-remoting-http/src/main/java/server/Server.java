package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import bank.AccountService;

@SpringBootApplication
public class Server {

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

	@Bean(name = "/HttpInvoke")
	public HttpInvokerServiceExporter httpInvokerServiceExporter(AccountService service) {
	    HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
	    exporter.setServiceInterface(AccountService.class);
	    exporter.setService(service);
	    return exporter;
	}

	@Bean(name = "/Hessian")
	public HessianServiceExporter hessianServiceExporter(AccountService service) {
		HessianServiceExporter exporter = new HessianServiceExporter();
	    exporter.setServiceInterface(AccountService.class);
	    exporter.setService(service);
	    return exporter;
	}

}

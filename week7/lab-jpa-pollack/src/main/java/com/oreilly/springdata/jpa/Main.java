package com.oreilly.springdata.jpa;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.oreilly.springdata.jpa.repository.CustomerRepository;
import com.oreilly.springdata.jpa.repository.OrderRepository;
import com.oreilly.springdata.jpa.repository.ProductRepository;

@SpringBootApplication
@Transactional
public class Main implements CommandLineRunner {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Main.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
	}
	
}
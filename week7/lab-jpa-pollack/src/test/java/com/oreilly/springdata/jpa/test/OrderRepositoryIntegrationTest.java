/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oreilly.springdata.jpa.test;

import static com.oreilly.springdata.jpa.test.matchers.CoreMatchers.named;
import static com.oreilly.springdata.jpa.test.matchers.CoreMatchers.with;
import static com.oreilly.springdata.jpa.test.matchers.OrderMatchers.LineItem;
import static com.oreilly.springdata.jpa.test.matchers.OrderMatchers.Product;
import static com.oreilly.springdata.jpa.test.matchers.OrderMatchers.containsOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.oreilly.springdata.jpa.model.Address;
import com.oreilly.springdata.jpa.model.Customer;
import com.oreilly.springdata.jpa.model.EmailAddress;
import com.oreilly.springdata.jpa.model.LineItem;
import com.oreilly.springdata.jpa.model.Order;
import com.oreilly.springdata.jpa.model.Product;
import com.oreilly.springdata.jpa.repository.CustomerRepository;
import com.oreilly.springdata.jpa.repository.OrderRepository;
import com.oreilly.springdata.jpa.repository.ProductRepository;

/**
 * Integration tests for {@link OrderRepository}.
 * 
 * @author Oliver Gierke
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderRepositoryIntegrationTest {
	
	@PersistenceContext
	EntityManager em;

	@Autowired
	OrderRepository repository;

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	ProductRepository productRepository;

	@Test
	public void createOrder() {

		Customer dave = customerRepository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
		Product iPad = productRepository.findOne(1L);

		Address adr = dave.getAddresses().iterator().next();
		Order order = new Order(dave, adr);
		order.add(new LineItem(iPad));

		order = repository.save(order);
		assertThat(order.getId(), is(notNullValue()));
		em.flush();
	}

	@Test
	public void readOrder() {

		Customer dave = customerRepository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
		List<Order> orders = repository.findByCustomer(dave);
		Matcher<Iterable<? super Order>> hasOrderForiPad = containsOrder(with(LineItem(with(Product(named("iPad"))))));

		assertThat(orders, hasSize(1));
		assertThat(orders, hasOrderForiPad);
	}
}

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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.oreilly.springdata.jpa.model.Address;
import com.oreilly.springdata.jpa.model.Customer;
import com.oreilly.springdata.jpa.model.EmailAddress;
import com.oreilly.springdata.jpa.repository.CustomerRepository;

/**
 * Integration tests for {@link CustomerRepository}.
 * 
 * @author Oliver Gierke
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerRepositoryIntegrationTest {

	@Autowired
	CustomerRepository repository;
	
	@PersistenceContext
	EntityManager em;

	@Test
	public void savesCustomerCorrectly() {

		EmailAddress email = new EmailAddress("alicia@keys.com");

		Customer dave = new Customer("Alicia", "Keys");
		dave.setEmailAddress(email);
		dave.add(new Address("27 Broadway", "New York", "United States"));

		Customer result = repository.save(dave);
		assertThat(result.getId(), is(notNullValue()));
	}

	@Test
	public void readsCustomerByEmail() {

		EmailAddress email = new EmailAddress("alicia@keys.com");
		Customer alicia = new Customer("Alicia", "Keys");
		alicia.setEmailAddress(email);

		repository.save(alicia);

		Customer result = repository.findByEmailAddress(email);
		assertThat(result, is(alicia));
	}

	@Test(expected=org.springframework.dao.DataIntegrityViolationException.class)
	public void preventsDuplicateEmail() {

		Customer dave = repository.findByEmailAddress(new EmailAddress("dave@dmband.com"));

		Customer anotherDave = new Customer("Dave", "Matthews");
		anotherDave.setEmailAddress(dave.getEmailAddress());

		repository.save(anotherDave);
	}

	@Test
	public void insertsNewCustomerCorrectly() {

		Customer customer = new Customer("Alicia", "Keys");
		customer = repository.save(customer);

		assertThat(customer.getId(), is(notNullValue()));
	}

	@Test
	public void updatesCustomerCorrectly() {

		Customer dave = repository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
		assertThat(dave, is(notNullValue()));

		dave.setLastname("Miller");
		dave = repository.save(dave);

		Customer reference = repository.findByEmailAddress(dave.getEmailAddress());
		assertThat(reference.getLastname(), is(dave.getLastname()));
	}

}

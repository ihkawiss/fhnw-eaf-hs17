package ch.fhnw.edu.rental.persistence.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.persistence.UserRepository;

@Repository
public class JpaUserRepository implements UserRepository {

	@Override
	public User findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}}

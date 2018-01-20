package ch.fhnw.edu.rental.persistence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.persistence.RentalRepository;
import ch.fhnw.edu.rental.persistence.UserRepository;

@Component
public class UserRepositoryImpl implements UserRepository {
	private Map<Long,User> data = new HashMap<Long,User>();
	private long nextId = 1;
	
	@Autowired
	private RentalRepository rentalRepo;
	
	@SuppressWarnings("unused")
	private void initData () {
		data.clear();
		nextId = 1;
		save(new User("Keller", "Marc"));
		save(new User("Knecht", "Werner"));
		save(new User("Meyer", "Barbara"));
		save(new User("Kummer", "Adolf"));
		
		findOne(1L).setEmail("marc.keller@gmail.com");
		findOne(2L).setEmail("werner.knecht@gmail.com");
		findOne(3L).setEmail("barbara.meyer@gmail.com");
		findOne(4L).setEmail("adolf.kummer@gmail.com");
	}

	@Override
	public User findOne(Long id) {
		if(id == null) throw new IllegalArgumentException();
		return data.get(id);
	}

	@Override
	public List<User> findAll() {
		return new ArrayList<User>(data.values());
	}

	@Override
	public User save(User user) {
		if (user.getId() == null)
			user.setId(nextId++);
		data.put(user.getId(), user);
		return user;
	}

	@Override
	public void delete(User user) {
		if(user == null) throw new IllegalArgumentException();
		for(Rental r : user.getRentals()){
			rentalRepo.delete(r);
		}
		data.remove(user.getId());
		user.setId(null);
	}

	@Override
	public void delete(Long id) {
		if(id == null) throw new IllegalArgumentException();
		delete(findOne(id));
	}

	@Override
	public boolean exists(Long id) {
		if(id == null) throw new IllegalArgumentException();
		return data.get(id) != null;
	}

	@Override
	public long count() {
		return data.size();
	}

	@Override
	public List<User> findByLastName(String lastName) {
		List<User> result = new ArrayList<>();
		for(User u : data.values()){
			if(u.getLastName().equals(lastName)) result.add(u);
		}
		return result;
	}

	@Override
	public List<User> findByFirstName(String firstName) {
		List<User> result = new ArrayList<>();
		for(User u : data.values()){
			if(u.getFirstName().equals(firstName)) result.add(u);
		}
		return result;
	}

	@Override
	public List<User> findByEmail(String email) {
		List<User> result = new ArrayList<>();
		for(User u : data.values()){
			if(u.getEmail().equals(email)) result.add(u);
		}
		return result;
	}

}

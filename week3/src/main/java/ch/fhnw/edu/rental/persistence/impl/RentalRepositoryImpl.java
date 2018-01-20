package ch.fhnw.edu.rental.persistence.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.persistence.MovieRepository;
import ch.fhnw.edu.rental.persistence.RentalRepository;
import ch.fhnw.edu.rental.persistence.UserRepository;

@Component
public class RentalRepositoryImpl implements RentalRepository {
	private Map<Long, Rental> data = new HashMap<Long, Rental>();
	private long nextId = 1;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private MovieRepository movieRepo;
	
	@SuppressWarnings("unused")
	private void initData () {
		data.clear();
		nextId = 1;
		Calendar cal = new GregorianCalendar(2016, GregorianCalendar.SEPTEMBER, 23);
		Rental r = new Rental(userRepo.findOne(1L), movieRepo.findOne(1L), 7, cal.getTime());
		save(r);
		cal = new GregorianCalendar(2016, GregorianCalendar.SEPTEMBER, 25);
		r = new Rental(userRepo.findOne(1L), movieRepo.findOne(2L), 6, cal.getTime());
		save(r);
		cal = new GregorianCalendar(2016, GregorianCalendar.SEPTEMBER, 27);
		r = new Rental(userRepo.findOne(3L), movieRepo.findOne(3L), 6, cal.getTime());
		save(r);
	}
	
	@Override
	public Rental findOne(Long id) {
		if(id == null) throw new IllegalArgumentException();
		return data.get(id);
	}

	@Override
	public List<Rental> findAll() {
		return new ArrayList<Rental>(data.values());
	}

	@Override
	public List<Rental> findByUser(User user) {
		List<Rental> res = new ArrayList<Rental>();
		for(Rental r : data.values()){
			if(r.getUser().equals(user)) res.add(r);
		}
		return res;
	}

	@Override
	public Rental save(Rental rental) {
		if (rental.getId() == null)
			rental.setId(nextId++);
		data.put(rental.getId(), rental);
		return rental;
	}

	@Override
	public void delete(Rental rental) {
		if(rental == null) throw new IllegalArgumentException();
		data.remove(rental.getId());
		rental.setId(null);
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

}

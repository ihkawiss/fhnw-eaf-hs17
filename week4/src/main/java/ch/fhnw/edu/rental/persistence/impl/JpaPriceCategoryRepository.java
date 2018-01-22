package ch.fhnw.edu.rental.persistence.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.PriceCategory;
import ch.fhnw.edu.rental.persistence.PriceCategoryRepository;

@Repository
public class JpaPriceCategoryRepository implements PriceCategoryRepository {

	@Override
	public PriceCategory findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PriceCategory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PriceCategory save(PriceCategory entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PriceCategory entity) {
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


}

package ch.fhnw.edu.rental.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.model.PriceCategory;
import ch.fhnw.edu.rental.persistence.PriceCategoryRepository;

@Repository
public class JpaPriceCategoryRepository implements PriceCategoryRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public PriceCategory findOne(Long id) {
		return em.find(PriceCategory.class, id);
	}

	@Override
	public List<PriceCategory> findAll() {
		TypedQuery<PriceCategory> query = em.createQuery("SELECT pc FROM PriceCategory pc", PriceCategory.class);
		return query.getResultList();
	}

	@Override
	public PriceCategory save(PriceCategory entity) {
		return em.merge(entity);
	}

	@Override
	public void delete(Long id) {
		em.remove(em.getReference(PriceCategory.class, id));
	}

	@Override
	public void delete(PriceCategory entity) {
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}

	@Override
	public boolean exists(Long id) {
		return findOne(id) != null;
	}

	@Override
	public long count() {
		return em.createQuery("SELECT COUNT(pc) FROM PriceCategory pc", Long.class).getSingleResult();
	}


}

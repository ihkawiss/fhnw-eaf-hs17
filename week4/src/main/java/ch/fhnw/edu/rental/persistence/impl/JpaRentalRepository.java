package ch.fhnw.edu.rental.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.persistence.RentalRepository;

@Repository
public class JpaRentalRepository implements RentalRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Rental findOne(Long id) {
		return em.find(Rental.class, id);
	}

	@Override
	public List<Rental> findAll() {
		TypedQuery<Rental> query = em.createNamedQuery("Rental.all", Rental.class);
		return query.getResultList();
	}

	@Override
	public Rental save(Rental entity) {
		return em.merge(entity);
	}

	@Override
	public void delete(Long id) {
		em.remove(em.getReference(Rental.class, id));
	}

	@Override
	public void delete(Rental entity) {
		Rental inCtx = em.contains(entity) ? entity : em.merge(entity);
		
		// free movie is it's rented
		if(inCtx.getMovie().isRented()) {
			inCtx.getMovie().setRented(false);
			em.persist(inCtx.getMovie());
		}
		
		em.remove(inCtx);
	}

	@Override
	public boolean exists(Long id) {
		return findOne(id) != null;
	}

	@Override
	public long count() {
		return em.createNamedQuery("Rental.count", Long.class).getSingleResult();
	}

	@Override
	public List<Rental> findByUser(User user) {
		return user.getRentals();
	}

}

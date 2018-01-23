package ch.fhnw.edu.rental.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.model.PriceCategory;
import ch.fhnw.edu.rental.persistence.MovieRepository;

@Repository
public class JpaMovieRepository implements MovieRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Movie findOne(Long id) {
		return em.find(Movie.class, id);
	}

	@Override
	public List<Movie> findAll() {
		TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class);
		return query.getResultList();
	}

	@Override
	public Movie save(Movie entity) {
		return em.merge(entity);
	}

	@Override
	public void delete(Long id) {
		em.remove(em.getReference(Movie.class, id));
	}

	@Override
	public void delete(Movie entity) {
		em.remove(entity);
	}

	@Override
	public boolean exists(Long id) {
		return findOne(id) != null;
	}

	@Override
	public long count() {
		return em.createQuery("SELECT COUNT(m) FROM Movie m", Long.class).getSingleResult();
	}

	@Override
	public List<Movie> findByTitle(String title) {
		TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m WHERE m.title = :title", Movie.class);
		query.setParameter("title", title);
		return query.getResultList();
	}

}

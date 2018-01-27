package ch.fhnw.edu.rental.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ch.fhnw.edu.rental.model.User;
import ch.fhnw.edu.rental.persistence.UserRepository;

@Repository
public class JpaUserRepository implements UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User findOne(Long id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = em.createNamedQuery("User.all", User.class);
		return query.getResultList();
	}

	@Override
	public User save(User entity) {
		return em.merge(entity);
	}

	@Override
	public void delete(Long id) {
		em.remove(em.getReference(User.class, id));
	}

	@Override
	public void delete(User entity) {
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}

	@Override
	public boolean exists(Long id) {
		return findOne(id) != null;
	}

	@Override
	public long count() {
		return em.createNamedQuery("User.count", Long.class).getSingleResult();
	}

	@Override
	public List<User> findByLastName(String lastName) {
		TypedQuery<User> query = em.createNamedQuery("User.byLastname", User.class);
		query.setParameter("lastName", lastName);
		return query.getResultList();
	}

	@Override
	public List<User> findByFirstName(String firstName) {
		TypedQuery<User> query = em.createNamedQuery("User.byFirstname", User.class);
		query.setParameter("firstName", firstName);
		return query.getResultList();
	}

	@Override
	public List<User> findByEmail(String email) {
		TypedQuery<User> query = em.createNamedQuery("User.byMail", User.class);
		query.setParameter("email", email);
		return query.getResultList();
	}}

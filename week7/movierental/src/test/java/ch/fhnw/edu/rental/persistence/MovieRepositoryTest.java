package ch.fhnw.edu.rental.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.edu.rental.model.Movie;

@RunWith(SpringRunner.class)
@SpringBootTest(properties={"gui=false"})
@Transactional
public class MovieRepositoryTest {
	
	@Autowired
	private MovieRepository repo;
	
	@Test
	public void testCount() {
		assertEquals("expected 5 movies in the movie repo", 5, repo.count());
	}
	
	@Test
	public void testExists() {
		assertTrue("movie with id 5 should exist in repo", repo.exists(5L));
		assertFalse("movie with id 55 should not exist in repo", repo.exists(55L));
	}
	
	@Test
	public void testDeleteId1() {
		repo.delete(5L);
		assertEquals("expected 4 movies after deleting movie with id 5", 4, repo.count());
	}
	
	@Test
	public void testDeleteId2() {
		try {
			repo.delete(11L);
			fail("expected an exception when deleting a non-existing entity");
		} catch(Exception e) {
			// OK
		}
	}
	
	@Test
	public void findAll() {
		List<Movie> list = repo.findAll();
		assertEquals("expected to load 5 movies", 5, list.size());
		assertTrue(list.contains(repo.findOne(1L)));
		assertTrue(list.contains(repo.findOne(2L)));
		assertTrue(list.contains(repo.findOne(3L)));
		assertTrue(list.contains(repo.findOne(4L)));
		assertTrue(list.contains(repo.findOne(5L)));
	}
	
}

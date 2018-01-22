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

import ch.fhnw.edu.rental.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(properties={"gui=false"})
@Transactional
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repo;
	
	@Test
	public void testCount() {
		assertEquals("expected 4 users in the user repo", 4, repo.count());
	}
	
	@Test
	public void testExists() {
		assertTrue("user with id 4 should exist in repo", repo.exists(4L));
		assertFalse("user with id 44 should not exist in repo", repo.exists(44L));
	}
	
	@Test
	public void testDeleteId1() {
		repo.delete(4L);
		assertEquals("expected 3 users after deleting movie with id 4", 3, repo.count());
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
		List<User> list = repo.findAll();
		assertEquals("expected to load 5 users", 4, list.size());
		assertTrue(list.contains(repo.findOne(1L)));
		assertTrue(list.contains(repo.findOne(2L)));
		assertTrue(list.contains(repo.findOne(3L)));
		assertTrue(list.contains(repo.findOne(4L)));
	}
	
}

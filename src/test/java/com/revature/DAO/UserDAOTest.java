package com.revature.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.TestUtilities;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserDAOTest {
	private TestUtilities td = new TestUtilities();
	private UserDAO ud;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtilities.clearDB();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ud = new UserDAO();
	}

	@After
	public void tearDown() throws Exception {
		HibernateUtil.closeSession();
		TestUtilities.clearDB();
	}

	@Test
	public void testSaveUser() {
		assertTrue(ud.saveUser(td.u1));
	}
	
	@Test
	public void testSaveUserDuplicate() {
		assertTrue(ud.saveUser(td.u1));
		int n = ud.findAll().size();
		ud.saveUser(td.u1);
		assertTrue(n == ud.findAll().size());
	}
	
	@Test
	public void testSaveUserNull() {
		assertFalse(ud.saveUser(null));
	}
	
	@Test
	public void testSaveUserMissingReq() {
		User u = new User();
		int n = ud.findAll().size();
		ud.saveUser(u);
		
		// check number of records hasn't changed
		assertTrue(n == ud.findAll().size());
	}
	
	@Test
	public void testDeleteUser() {
		ud.saveUser(td.u1);
		assertTrue(ud.deleteUser(td.u1));
		assertTrue(ud.findUser(td.u1.getUsername()) == null);
	}
	
	@Test
	public void testDeleteNull() {
		assertFalse(ud.deleteUser(null));
	}
	
	@Test
	public void testDeleteNonExistant() {
		ud.saveUser(td.u1);
		ud.deleteUser(td.u2);
		assertTrue(ud.findAll().size() == 1);
	}
	
	@Test
	public void testUpdateUser() {
		ud.saveUser(td.u1);
		User temp = td.u1;
		temp.setEmail("a@b.c");
		assertTrue(ud.updateUser(temp));
		assertEquals(ud.findUser(td.u1.getUsername()).getEmail(), "a@b.c");
	}
	
	@Test
	public void testUpdateUserNull() {
		assertFalse(ud.updateUser(null));
	}
	
	@Test
	public void testFindAll() {
		// generate random number of users between 1 and 10
		// check if return size is consistent
		Random rand = new Random();
		int n = rand.nextInt(9);
		
		for (int i=0; i < n; i++) {
			User temp = new User();
			temp.setUsername("user" + i);
			ud.saveUser(temp);
		}
		assertTrue(n == ud.findAll().size());
	}
	
	@Test
	public void testFindAllEmpty() {
		assertTrue(0 == ud.findAll().size());
	}

}

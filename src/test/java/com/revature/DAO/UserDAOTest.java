package com.revature.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.revature.TestUtilities;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void testDeleteNonExistent() {
		ud.saveUser(td.u1);
		ud.deleteUser(td.u2);
		List<User> all = ud.findAll();
		for (User u : all) {
			System.out.println(u);
		}
		assertTrue(all.size() == 1);
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
	public void testUpdateUserInvalid() {
		User u = new User();
		assertFalse(ud.updateUser(u));
		u.setUsername(null);
		assertFalse(ud.updateUser(u));
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
		
		List<User> all = ud.findAll();
		
		for (User u : all) {
			System.out.println(u);
		}
		
		assertTrue(n == all.size());
	}
	
	@Test
	public void testFindAllEmpty() {
		assertTrue(0 == ud.findAll().size());
	}
	
	@Test
	public void testFindUser() {
		ud.saveUser(td.u1);
		assertEquals(td.u1,ud.findUser(td.u1.getUsername()));
	}
	
	@Test
	public void testFindUserNull() {
		assertEquals(null, ud.findUser(null));
	}
	
	@Test
	public void testFindUserMissing() {
		assertEquals(null, ud.findUser("divgh837gh38q7gh"));
	}
	
	@Test
	public void testFindByUsername() {
		ud.saveUser(td.u1);
		assertEquals(td.u1,ud.findByUsername(td.u1.getUsername()));
	}
	
	@Test
	public void testFindByUsernameNull() {
		assertEquals(null, ud.findByUsername(null));
	}
	
	@Test
	public void testFindByUsernameMissing() {
		assertEquals(null, ud.findByUsername("divgh837gh38q7gh"));
	}

}

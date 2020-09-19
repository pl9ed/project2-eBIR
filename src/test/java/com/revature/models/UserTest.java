package com.revature.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {
	User u = new User();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmailRegex() {
		String email = "a@b.c";
		assertTrue(u.setEmail(email));
		assertEquals(email, u.getEmail());
	}
	
	@Test
	public void testEmailRegexFail() {
		String email = "a";
		assertFalse(u.setEmail(email));
		assertFalse(email.equals(u.getEmail()));
		
		email = "a@b";
		assertFalse(u.setEmail(email));
		assertFalse(email.equals(u.getEmail()));
		
		email = "@b.c";
		assertFalse(u.setEmail(email));
		assertFalse(email.equals(u.getEmail()));
		
		email = "a@b.";
		assertFalse(u.setEmail(email));
		assertFalse(email.equals(u.getEmail()));
	}
	
	@Test
	public void testEmailRegexNull() {
		assertFalse(u.setEmail(null));
	}

}

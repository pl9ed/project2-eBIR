package com.revature.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {
	User u;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		u = new User();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmailRegex() {
		String email = "a@b.c";
		assertTrue(u.setEmail(email));
		assertEquals(email, u.getEmail());
		
		email = "abc@abc.com";
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
		
		email = "@.";
		assertFalse(u.setEmail(email));
		assertFalse(email.equals(u.getEmail()));
	}
	
	@Test
	public void testEmailRegexNull() {
		assertFalse(u.setEmail(null));
	}

	@Test
	public void testPassHash() {
		String pass = "abc123";
		u.setPassword(pass);
		assertFalse(u.getPassword().equals(pass));
	}
	
	@Test
	public void testPassHashEmpty() {
		// needed since password defaults to empty string instead of null
		u.setPassword("temp");
		
		String pass = "";
		u.setPassword(pass);
		assertFalse(u.getPassword().equals(""));
	}
	
	@Test
	public void testPassHashNull() {
		u.setPassword("abc");
		u.setPassword(null);
		assertFalse(u.getPassword() == null);
	}
	
	@Test
	public void testPassCheck() {
		String pass = "abc";
		u.setPassword(pass);
		assertTrue(u.checkPassword(pass));
	}
	
	@Test
	public void testPassCheckFail() {
		String pass = "abc";
		u.setPassword(pass);
		assertFalse(u.checkPassword("xyz"));
	}
	
	@Test
	public void testPassCheckEmpty() {
		assertFalse(u.checkPassword(""));
	}
	
	@Test
	public void testPassCheckNull() {
		assertFalse(u.checkPassword(null));
	}
}

package com.revature.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.util.HibernateUtil;

public class UserTest {
	User u;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HibernateUtil.reconfigureSchema("public");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HibernateUtil.reconfigureSchema(System.getenv("project2_schema"));
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
		u.setPasswordPlain(pass);
		assertFalse(u.getPassword().equals(pass));
	}
	
	@Test
	public void testPassHashEmpty() {
		// needed since password defaults to empty string instead of null
		u.setPasswordPlain("temp");
		
		String pass = "";
		u.setPasswordPlain(pass);
		assertFalse(u.getPassword().equals(""));
	}
	
	@Test
	public void testPassHashNull() {
		u.setPasswordPlain("abc");
		u.setPasswordPlain(null);
		assertFalse(u.getPassword() == null);
	}
	
	@Test
	public void testPassCheck() {
		String pass = "abc";
		u.setPasswordPlain(pass);
		assertTrue(u.checkPassword(pass));
	}
	
	@Test
	public void testPassCheckFail() {
		String pass = "abc";
		u.setPasswordPlain(pass);
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

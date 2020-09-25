package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.TestUtilities;
import com.revature.DAO.IUserDAO;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserServiceTest {
	
	private TestUtilities td;
	private String updateString = "abc123";
	
	@Mock
	IUserDAO ud;
	
	@InjectMocks
	private UserService us;

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
		td = new TestUtilities();
		us = new UserService();
		td.u1.setPasswordPlain("pass");
		
		MockitoAnnotations.initMocks(this);
		when(ud.findByUsername("u1")).thenReturn(td.u1);
		when(ud.findUser("u1")).thenReturn(td.u1);
		when(ud.updateUser(any(User.class))).thenReturn(true);
		when(ud.updateUser(null)).thenReturn(false);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin() {
		User temp = us.login("u1", "pass");
		assertEquals(td.u1, temp);
	}
	
	@Test
	public void testLoginWrongPass() {
		assertTrue(null == us.login("u1", "wrong"));
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void testLoginNoUser() {
		us.login("NOT A REAL USER", "");
	}
	
	@Test
	public void testUpdateFirstName() {
		assertEquals(updateString, us.updateFirstName(td.u1, updateString));
		assertFalse("wrong".equals(us.updateFirstName(td.u1, updateString)));
	}
	
	@Test
	public void testUpdateFirstNameNullUser() {
		assertTrue(null == us.updateFirstName(null, updateString));
	}
	
	@Test
	public void testUpdateFirstNameNullUsername() {
		assertTrue(null == us.updateFirstName(td.u1, null));
	}
	
	@Test
	public void testUpdateLastName() {
		assertEquals(updateString, us.updateLastName(td.u1, updateString));
		assertFalse("wrong".equals(us.updateLastName(td.u1, updateString)));
	}
	
	@Test
	public void testUpdateLastNameNullUser() {
		assertTrue(null == us.updateLastName(null, updateString));
	}
	
	@Test
	public void testUpdateLastNameNullUsername() {
		assertTrue(null == us.updateLastName(td.u1, null));
	}
	
	@Test
	public void testUpdatePassword() {
		String hash = us.updatePassword(td.u1, updateString);
		assertTrue(BCrypt.checkpw(updateString, hash));
		assertFalse(BCrypt.checkpw("wrong", hash));
	}
	
	@Test
	public void testUpdatePasswordNullUser() {
		assertTrue(null == us.updatePassword(null, updateString));
	}
	
	@Test
	public void testUpdatePasswordNullPassword() {
		// pw should still be pass, so update should return original value
		assertTrue(BCrypt.checkpw("pass", us.updatePassword(td.u1, null)));
	}
	
	@Test
	public void testUpdateEmail() {
		String email = "abc@aaa.b";
		assertEquals(email, us.updateEmail(td.u1, email));
		assertFalse("wrong".equals(us.updateEmail(td.u1, email)));
	}
	
	@Test
	public void testUpdateEmailNullUser() {
		String email = "abc@aaa.b";
		assertTrue(null == us.updateEmail(null, email));
	}
	
	@Test
	public void testUpdateEmailNullEmail() {
		assertTrue(null == us.updateEmail(td.u1, null));
	}
	
	@Test
	public void testUpdateEmailInvalidFormat() {
		// don't need to be comprehensive, since this is done in the user class
		assertTrue(null == us.updateEmail(td.u1, "aaa"));
	}
	
	@Test
	public void testUpdateUser() {
		assertTrue(us.updateUser(td.u1));
	}
	
	@Test
	public void testUpdateUserNull() {
		assertFalse(us.updateUser(null));
	}
	
	@Test
	public void testUpdateUserInvalidUsername() {
		User u = new User();
		
		// We set u to only have invalid params, so this is ok, assuming DAO works properly
		when(ud.updateUser(u)).thenReturn(false);
		
		// user with empty string username
		assertFalse(us.updateUser(u));
		
		// user with null username
		u.setUsername(null);
		assertFalse(us.updateUser(u));
	}

}

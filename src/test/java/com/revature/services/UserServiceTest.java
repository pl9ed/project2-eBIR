package com.revature.services;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.TestUtilities;
import com.revature.DAO.IUserDAO;
import com.revature.exceptions.ResourceNotFoundException;

public class UserServiceTest {
	
	private TestUtilities td;
	private UserService us;
	
	@Mock
	IUserDAO ud;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		td = new TestUtilities();
		us = new UserService();
		
		MockitoAnnotations.initMocks(this);
		when(ud.findUser("u1")).thenReturn(td.u1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testLoginWrongPass() {
		
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void testLoginNoUser() {
		us.login("NOT A REAL USER", "");
	}
	
	@Test
	public void testUpdateFirstName() {
		
	}
	
	@Test
	public void testUpdateFirstNameNullUser() {
		
	}
	
	@Test
	public void testUpdateFirstNameNullUsername() {
		
	}
	
	@Test
	public void testUpdateLastName() {
		
	}
	
	@Test
	public void testUpdateLastNameNullUser() {
		
	}
	
	@Test
	public void testUpdateLastNameNullUsername() {
		
	}
	
	@Test
	public void testUpdatePassword() {
		
	}
	
	@Test
	public void testUpdatePasswordNullUser() {
		
	}
	
	@Test
	public void testUpdatePasswordNullPassword() {
		
	}
	
	@Test
	public void testUpdateEmail() {
		
	}
	
	@Test
	public void testUpdateEmailNullUser() {
		
	}
	
	@Test
	public void testUpdateEmailNullEmail() {
		
	}
	
	@Test
	public void testUpdateEmailInvalidFormat() {
		
	}
	
	

}

package com.revature.controllers;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.TestUtilities;
import com.revature.services.UserService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class UserControllerTest {
	
	private TestUtilities td;
	
	@Mock
	private UserService us;
	
	@InjectMocks
	private UserController uc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		RestAssuredMockMvc.standaloneSetup(uc);
		td = new TestUtilities();
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRegisterNull() {
		
	}
	
	@Test
	public void testRegisterInvalidUser() {
		
	}
	
	@Test
	public void testLogin() {
		
	}
	
	@Test
	public void testLoginNull() {
		
	}
	
	@Test
	public void testLoginInvalid() {
		
	}
	
	@Test
	public void testLoginNoUser() {
		
	}
	
	@Test
	public void testUpdateFN() {
		
	}
	
	@Test
	public void testUpdateFNNull() {
		
	}
	
	@Test
	public void testUpdateFNInvalid() {
		
	}
	
	@Test
	public void testUpdateLN() {
		
	}
	
	@Test
	public void testUpdateLNNull() {
		
	}
	
	@Test
	public void testUpdateLNInvalid() {
		
	}
	
	@Test
	public void testChangePW() {
		
	}
	
	@Test
	public void testChangePWNull() {
		
	}
	
	@Test
	public void testChangePWInvalid() {
		
	}

}

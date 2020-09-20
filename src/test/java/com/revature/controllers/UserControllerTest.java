package com.revature.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.TestUtilities;
import com.revature.services.UserService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;



public class UserControllerTest {
	
	private TestUtilities td;
	private static ObjectMapper om = new ObjectMapper();
	
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
		td.u1.setPassword("pass");
		
		// td.u1 exists and will be able to login
		// can't use getPassword, since the user object does the hashing

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegister() throws JsonProcessingException {
		when(us.register("u2", td.u2.getPassword(), "", "", "")).thenReturn(td.u2);
		String json = om.writeValueAsString(td.u2);
		
		given()
			.standaloneSetup(uc)
			.body(json)
			.contentType("application/json")
		.when()
			.post("/user/register")
		.then()
			.statusCode(HttpStatus.SC_CREATED)
			.assertThat()
				.body("username", equalTo(td.u2.getUsername()));
	}
	
	@Test
	public void testRegisterInvalidUser() {
		when(us.register(null, null, null, null, null)).thenReturn(null);
		
		given()
			.standaloneSetup(uc)
			.body("")
			.contentType("application/json")
		.when()
			.post("/user/register")
		.then()
			.statusCode(400);
	}
	
	@Test
	public void testRegisterDuplicate() throws JsonProcessingException {
		when(us.register("u1", "pass", "", "", "")).thenReturn(null);
		String json = om.writeValueAsString(td.u1);
		
		given()
			.standaloneSetup(uc)
			.body(json)
			.contentType("application/json")
		.when()
			.post("/user/register")
		.then()
			.statusCode(409);
	}
	
	@Test
	public void testLogin() {
		when(us.login("u1", "pass")).thenReturn(td.u1);
		String json = om.writeValueAsString(td.u1);
		
		given()
			.standaloneSetup(uc)
			.body(json)
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
	public void testUpdateUser() {
		
	}
	
	@Test
	public void testUpdateUserNull() {
		
	}
	
	@Test
	public void testUpdateUserInvalid() {
		
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

package com.revature.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.TestUtilities;
import com.revature.DAO.IUserDAO;
import com.revature.util.HibernateUtil;

import io.restassured.module.mockmvc.RestAssuredMockMvc;


@ContextConfiguration(locations = "/applicationContext.xml")
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerWACTest {
	
	private TestUtilities td;
	private static ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private IUserDAO ud;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtilities.clearDB();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HibernateUtil.closeSession();
	}

	@Before
	public void setUp() throws Exception {
		RestAssuredMockMvc.webAppContextSetup(wac);
		td = new TestUtilities();

		td.u1.setPassword("pass");
		ud.saveUser(td.u1);
	}

	@After
	public void tearDown() throws Exception {
		HibernateUtil.closeSession();
		TestUtilities.clearDB();
	}

	@Test
	public void testRegister() throws JsonProcessingException {
		String json = om.writeValueAsString(td.u2);
		
		given()
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
		
		given()
			.body("")
			.contentType("application/json")
		.when()
			.post("/user/register")
		.then()
			.statusCode(400);
	}
	
	@Test
	public void testRegisterDuplicate() throws JsonProcessingException {
		String json = om.writeValueAsString(td.u1);
		
		given()
			.body(json)
			.contentType("application/json")
		.when()
			.post("/user/register")
		.then()
			.log().ifValidationFails()
			.statusCode(409);
	}
	
	@Test
	public void testLogin() {
		
		String json = "{ \"username\" : \"u1\","
				+ "\"password\" : \"pass\" }";
		
		given()
			.body(json)
			.contentType("application/json")
		.when()
			.post("/user/login")
		.then()
			.statusCode(200)
			.assertThat()
				.body("username", equalTo(td.u1.getUsername()));
	}
	
	@Test
	public void testLoginEmpty() {
		given()
			.body("")
			.contentType("application/json")
		.when()
			.post("/user/login")
		.then()
			.statusCode(400);
		
	}
	
	@Test
	public void testLoginInvalid() {
		String json = "{\"username\" : \"a\", \"pass\" : \"pass\"}";
		given()
			.body(json)
			.contentType("application/json")
		.when()
			.post("/user/login")
		.then()
			.statusCode(400);
	}
	
	@Test
	public void testLoginNoUser() {
		String json = "{\"username\" : \"u2\", \"password\" : \"pass\"}";
		
		given()
			.body(json)
			.contentType("application/json")
		.when()
			.post("/user/login")
		.then()
			.statusCode(401);
	}
	
	@Test
	public void testLoginWrongPassword() {
		String json = "{\"username\" : \"u1\", \"password\" : \"pass123\"}";
		
		given()
			.body(json)
			.contentType("application/json")
		.when()
			.post("/user/login")
		.then()
			.statusCode(401);
	}
	
	@Test
	public void testUpdateUser() throws JsonProcessingException {
		
		given()
			.body(om.writeValueAsString(td.u1))
			.contentType("application/json")
		.when()
			.put("/user/update")
		.then()
			.statusCode(200)
			.assertThat()
				.body("username", equalTo(td.u1.getUsername()));
	}
	
	@Test
	public void testUpdateUserInvalid() throws JsonMappingException, JsonProcessingException {		
		given()
			.body(om.writeValueAsString(null))
			.contentType("application/json")
		.when()
			.put("/user/update")
		.then()
			.statusCode(400);
	}
	
	/*
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
	*/
}

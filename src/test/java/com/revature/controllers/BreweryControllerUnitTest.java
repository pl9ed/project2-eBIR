package com.revature.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.TestUtilities;
import com.revature.DAO.IReviewDAO;
import com.revature.DAO.IUserDAO;
import com.revature.models.Review;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

//@ContextConfiguration(locations = "testContext.xml")
//@WebAppConfiguration
public class BreweryControllerUnitTest {
	
	private TestUtilities td;
	private ObjectMapper om = new ObjectMapper();
	
	@Mock
	private IReviewDAO rDAO;
	@Mock
	private IUserDAO uDAO;
	
	@InjectMocks
	private BreweryController bc;
	
//	private MockMvc mock;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
//		mock = MockMvcBuilders.standaloneSetup(new BreweryController()).build(); 
		RestAssuredMockMvc.standaloneSetup(bc); // Unit Test setup
		
		td = new TestUtilities();
		
		MockitoAnnotations.initMocks(this);
		when(rDAO.saveReview(any(Review.class))).thenReturn(true);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAddReview() throws Exception {
		String json = om.writeValueAsString(td.r1);
		given()
			.standaloneSetup(bc)
			.body(json)
			.contentType("application/json")
		.when()
			.post("/review")
		.then()
			.log().ifValidationFails()
			.statusCode(201);
	}

	@Test
	public void testAddReviewNoBody() throws Exception {
		given()
			.standaloneSetup(bc)
			.body("")
			.contentType("application/json")
		.when()
			.post("/review")
		.then()
			.log().ifValidationFails()
			.statusCode(400);
	}

}

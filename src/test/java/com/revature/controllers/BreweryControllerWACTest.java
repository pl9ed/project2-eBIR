package com.revature.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.TestUtilities;
import com.revature.DAO.IBreweryDAO;
import com.revature.DAO.IReviewDAO;
import com.revature.DAO.IUserDAO;
import com.revature.models.Review;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@ContextConfiguration(locations = "/applicationContext.xml")
@SpringBootTest
@RunWith(SpringRunner.class)
public class BreweryControllerWACTest {
	
	private TestUtilities td;
	private ObjectMapper om = new ObjectMapper();
	
	@Mock
	private IBreweryDAO bDAO;
	@Mock
	private IReviewDAO rDAO;
	@Mock
	private IUserDAO uDAO;
	
	@Autowired
	private WebApplicationContext wac;
	
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
//		RestAssuredMockMvc.standaloneSetup(bc); // Unit Test setup
		RestAssuredMockMvc.webAppContextSetup(wac);
		
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
		System.out.println(json);
		given()
			.webAppContextSetup(wac)
			.body(json)
			.contentType("application/json")
		.when()
			.post("/review")
		.then()
			//.log().all()
			.statusCode(201);
	}

	@Test
	public void testAddReviewNoBody() throws Exception {
//		mock.perform(post("/review").content(""))
//			.andDo(print());
		
		given()
			.webAppContextSetup(wac)
			.body("")
			.contentType("application/json")
		.when()
			.post("/review")
		.then()
			.statusCode(400);
	}

}

package com.revature.controllers;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.revature.DAO.BreweryDAO;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

public class BreweryControllerUnitTest {
	
	@Mock
	private BreweryDAO dao;
	
	@InjectMocks
	private BreweryController bc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		RestAssuredMockMvc.standaloneSetup(bc);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddReviewNoBody() {
		given()
		.when()
			.post("/review")
		.then()
			.log().all()
			.assertThat().statusCode(400);
	}

}

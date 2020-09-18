package com.revature.DAO;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.TestData;

public class ReviewDAOTest {
	private TestData td = new TestData();
	private ReviewDAO rd;
	private UserDAO ud;
	private BreweryDAO bd;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		rd = new ReviewDAO();
		ud = new UserDAO();
		bd = new BreweryDAO();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSaveReviewNull() {
	    assertFalse(ud.saveUser(null));
	}
	
	@Test
	public void testSaveReview() {   
	    ud.saveUser(td.u1);
	    bd.saveBrewery(td.b1);
	    
	    assertTrue(rd.saveReview(td.r1));
	}

}

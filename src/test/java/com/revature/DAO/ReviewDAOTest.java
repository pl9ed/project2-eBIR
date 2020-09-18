package com.revature.DAO;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.TestUtilities;
import com.revature.util.HibernateUtil;

public class ReviewDAOTest {
	private TestUtilities td = new TestUtilities();
	private ReviewDAO rd;
	private UserDAO ud;
	private BreweryDAO bd;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtilities.clearDB();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	    TestUtilities.clearDB();
		rd = new ReviewDAO();
		ud = new UserDAO();
		bd = new BreweryDAO();
		
	    ud.saveUser(td.u1);
	    bd.saveBrewery(td.b1);
	}

	@After
	public void tearDown() throws Exception {
	    TestUtilities.clearDB();
		HibernateUtil.closeSession();
	}
	
	@Test
	public void testSaveReviewNull() {
	    assertFalse(ud.saveUser(null));
	}
	
	@Test
	public void testSaveReview() {   
	    assertTrue(rd.saveReview(td.r1));
	}

}

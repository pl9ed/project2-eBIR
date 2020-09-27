package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

import com.revature.TestUtilities;
import com.revature.DAO.IReviewDAO;
import com.revature.models.Review;
import com.revature.util.HibernateUtil;


public class ReviewServiceTest {
	
	private TestUtilities td;
	
	@Mock
	private IReviewDAO rd;
	
	@InjectMocks
	private ReviewService rs = new ReviewService();;
	
	private Review invalid;

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

		MockitoAnnotations.initMocks(this);
		
		// td.r1 doesn't exist
		// td.r2 is an existing review in our DB
		when(rd.saveReview(td.r1)).thenReturn(true);
		when(rd.saveReview(td.r2)).thenReturn(false);
		when(rd.saveReview(null)).thenReturn(false);
		when(rd.saveReview(invalid)).thenReturn(false);
		
		when(rd.deleteReview(any(Review.class))).thenReturn(true);
		when(rd.deleteReview(null)).thenReturn(false);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertReview() {
		Review ret = rs.insertReview(td.r1);
		assertEquals(td.r1, ret);
	}
	
	@Test
	public void testInsertReviewNull() {
		Review ret = rs.insertReview(null);
		assertTrue(null == ret);
	}
	
	@Test
	public void testInsertReviewInvalid() {
		invalid = new Review();
		Review ret = rs.insertReview(invalid);
		assertTrue(null == ret);
	}
	
	@Test
	public void testInsertReviewDupl() {
		assertTrue(null == rs.insertReview(td.r2));
	}
	
	@Test
	public void testDeleteReview() {
		assertEquals(td.r2, rs.deleteReview(td.r2));
	}
	
	@Test
	public void testDeleteReviewNull() {
		assertTrue(null == rs.deleteReview(null));
	}
	
	@Test
	public void testDeleteReviewInvalid() {
		Review invalid = new Review();
		assertEquals(invalid, rs.deleteReview(invalid));
	}

}

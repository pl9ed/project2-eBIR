package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.TestUtilities;
import com.revature.DAO.IReviewDAO;
import com.revature.models.Review;
import com.revature.util.HibernateUtil;

class ReviewServiceTest {
	
	private TestUtilities td;
	
	@Mock
	private IReviewDAO rd;
	
	@InjectMocks
	private ReviewService rs;
	
	private Review invalid;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		HibernateUtil.reconfigureSchema("public");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		HibernateUtil.reconfigureSchema(System.getenv("project2_schema"));
	}

	@BeforeEach
	void setUp() throws Exception {
		td = new TestUtilities();
		rs = new ReviewService();
		
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

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testInsertReview() {
		assertEquals(td.r1, rs.insertReview(td.r1));
	}
	
	@Test
	public void testInsertReviewNull() {
		assertTrue(null == rs.insertReview(null));
	}
	
	@Test
	public void testInsertReviewInvalid() {
		invalid = new Review();
		assertTrue(null == rs.insertReview(invalid));
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

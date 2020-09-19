package com.revature.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
import com.revature.DAO.IBreweryDAO;
import com.revature.DAO.IReviewDAO;
import com.revature.DAO.IUserDAO;
import com.revature.models.Brewery;
import com.revature.models.Review;
import com.revature.models.User;

public class BreweryServiceTest {
	
	private TestUtilities td;
	
	@Mock
	IBreweryDAO bDAO;
	@Mock
	IReviewDAO rDAO;
	@Mock
	IUserDAO uDAO;	
	
	@InjectMocks
	private BreweryService bs;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		td = new TestUtilities();
		bs = new BreweryService();
		
		MockitoAnnotations.initMocks(this);
		
		// mock behavior
		when(bDAO.updateBrewery(td.b1)).thenReturn(true);
		when(rDAO.saveReview(td.r1)).thenReturn(true);
		when(uDAO.updateUser(td.u1)).thenReturn(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddReview() {
		assertTrue(bs.addReview(td.r1));
	}
	
	@Test
	public void testAddReviewNull() {
		assertFalse(bs.addReview(null));
	}
	
	@Test
	public void testAddReviewMissing() {
		Review r = new Review();
		
		assertFalse(bs.addReview(r));
	}
	
	@Test
	public void testUpdateRating() {
		assertTrue(bs.updateRating(1, td.b1));
		assertTrue(bs.updateRating(3, td.b1));
		
		assertTrue(2 == td.b1.getRating());
		assertTrue(2 == td.b1.getNumberofRatings());
	}
	
	@Test
	public void testUpdateRatingReviewValue() {
		bs.updateRating(1, td.b1);
		assertTrue(1 == td.b1.getNumberofRatings());
		assertTrue(1 == td.b1.getRating());
		
		bs.updateRating(3, td.b1);
		
		assertTrue(2 == td.b1.getRating());
		assertTrue(2 == td.b1.getNumberofRatings());
	}
	
	@Test
	public void testUpdateRatingEqual() {
		bs.updateRating(1, td.b1);
		bs.updateRating(1, td.b1);
		
		assertTrue (1 == td.b1.getRating());
		assertTrue(td.b1.getNumberofRatings() > 1);
	}
	
	@Test
	public void testUpdateRatingOutofBounds() {
		assertFalse(bs.updateRating(-1, td.b1));
		assertFalse(bs.updateRating(100000, td.b1));
		
		// make sure object hasn't been updated even if returns false
		assertTrue(td.b1.getRating() == 0);
	}
	
	@Test
	public void testAddFavorite() {
		assertTrue(bs.addFavorite(td.u1, td.b1));
	}
	
	@Test
	public void testAddFavoriteNullUser() {
		assertFalse(bs.addFavorite(null, td.b1));
	}
	
	@Test
	public void testAddFavoriteNullBrewery() {
		assertFalse(bs.addFavorite(td.u1, null));
	}
	
	@Test
	public void testAddFavoriteInValidUser() {
		User u = new User();
		assertFalse(bs.addFavorite(u, td.b1));
	}
	
	@Test
	public void testAddFavoriteInvalidBrew() {
		Brewery b = new Brewery();
		assertFalse(bs.addFavorite(td.u1, b));
	}
	
	@Test
	public void testRemoveFavorite() {
		td.u1.getFavorites().add(td.b1);
		assertTrue(bs.removeFavorite(td.u1, td.b1));
		assertTrue(td.u1.getFavorites().size() == 0);
	}
	
	@Test
	public void testRemoveFavoriteNullUser() {
		assertFalse(bs.removeFavorite(null, td.b1));
	}
	
	@Test
	public void testRemoveFavoriteNullBrewery() {
		assertFalse(bs.removeFavorite(td.u1, null));
	}
	
	@Test
	public void testRemoveFavoriteInValidUser() {
		User u = new User();
		assertFalse(bs.removeFavorite(u, td.b1));
	}
	
	@Test
	public void testRemoveFavoriteInvalidBrew() {
		Brewery b = new Brewery();
		assertFalse(bs.removeFavorite(td.u1, b));
	}


}

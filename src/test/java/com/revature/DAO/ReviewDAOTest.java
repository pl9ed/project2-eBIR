package com.revature.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.revature.TestUtilities;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReviewDAOTest {
	private TestUtilities td = new TestUtilities();
	private ReviewDAO rd;
	private UserDAO ud;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HibernateUtil.reconfigureSchema("public");
		TestUtilities.clearDB();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HibernateUtil.reconfigureSchema(System.getenv("project2_schema"));
	}

	@Before
	public void setUp() throws Exception {
		rd = new ReviewDAO();
		ud = new UserDAO();
		
	    ud.saveUser(td.u1); ud.saveUser(td.u2);	    
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

	@Test
	public void testDelete() {
		rd.saveReview(td.r1);
		assertTrue(rd.deleteReview(td.r1));
	}
	
	@Test
	public void testDeleteNull() {
		assertFalse(rd.deleteReview(null));
	}
	
	@Test
	public void testDeleteNonExistent() {
		int n = rd.findAll().size();
		rd.deleteReview(td.r1);
		assertTrue(n == rd.findAll().size());
	}
	
	@Test
	public void testFind() {
		rd.saveReview(td.r1);
		
		assertEquals(td.r1, rd.find(td.r1.getId()));
	}
	
	@Test
	public void testFindNonExistent() {
		assertTrue(null == rd.find(-1));
	}
	
	@Test
	public void testUpdate() {
		rd.saveReview(td.r1);
		Review temp = td.r1;
		String newText = "updated";
		temp.setReviewText(newText);
		assertTrue(rd.updateReview(temp));
		Review r = rd.find(td.r1.getId());
		assertEquals(newText, r.getReviewText());
	}
	
	@Test
	public void testUpdateNull() {
		assertFalse(rd.updateReview(null));
	}
	
	@Test
	public void testUpdateMissingReq() {
		Review missing = new Review();
		assertFalse(rd.updateReview(missing));
	}
	
	@Test
	public void testUpdateInvalidReq() {
		Review invalid = td.r1;
		Review invalid2 = td.r1;
		
		// review for brewery that doesn't exist in DB
		invalid.setBrewery(-1);
		invalid2.setSubmitter(new User());
		
		assertFalse(rd.updateReview(invalid));
		assertFalse(rd.updateReview(invalid2));
	}
	
	@Test
	public void testUpdateTransOpen() {
		Transaction tx = HibernateUtil.getSession().getTransaction();
		tx.begin();
		
		assertFalse(rd.updateReview(td.r1));
	}
	
	@Test
	public void testFindAll() {
		rd.saveReview(td.r1);
		assertTrue(1 == rd.findAll().size());
		
		rd.saveReview(td.r2);
		// this works
//		List<Review> all = new ArrayList<>(rd.findAll());
		// this doesn't work???????
		// Set<Review all = rd.findAll();
		List<Review> all = rd.findAll();
		assertTrue(2 == all.size());
		System.out.println(all);
		System.out.println(td.r1);
		System.out.println(td.r2);
		assertTrue(all.contains(td.r1));
		assertTrue(all.contains(td.r2));
	}
	
	@Test
	public void testFindAllEmpty() {
		assertTrue(0 == rd.findAll().size());
	}
	
	@Test
	public void testFindByUser() {
		rd.saveReview(td.r1);
		
		assertTrue(rd.findByUser(td.u1).contains(td.r1));
		assertTrue(rd.findByUser(td.u2).size() == 0);
	}
	
	@Test
	public void testFindByUserNull() {
		assertTrue(rd.findByUser((User) null).size() == 0);
	}
	
	@Test
	public void testFindByUsername() {
		rd.saveReview(td.r1);
		assertTrue(rd.findByUser(td.u1.getUsername()).contains(td.r1));
		assertTrue(rd.findByUser(td.u2.getUsername()).size() == 0);
	}
	
	@Test
	public void testFindByUsernameNull() {
		assertTrue(rd.findByUser((String) null).size() == 0);
	}
	
	@Test
	public void testFindByUsernameNonExistent() {
		assertTrue(rd.findByUser("adsfasfqgfw34gfw34fg3").size() == 0);
	}
	
	@Test
	public void testFindByBrewery() {
		rd.saveReview(td.r1);
		Set<Review> ret = rd.findByBrewery(td.r1.getBrewery());
		assertTrue(ret.size() == 1);
		assertTrue(rd.findByBrewery(1).contains(td.r1));
	}
	
	@Test
	public void testFindByBreweryInvalid() {
		assertTrue(rd.findByBrewery(99999).size()==0);
		assertTrue(rd.findByBrewery(-1).size() == 0);
	}
	
	
}

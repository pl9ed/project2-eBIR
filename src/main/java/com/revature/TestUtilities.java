package com.revature;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.DAO.ReviewDAO;
import com.revature.DAO.UserDAO;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.HibernateUtil;

@Repository
public class TestUtilities {
	
//	@Autowired
//	private static UserService us;
//	@Autowired
//	private static ReviewService rs;
	
	public User u1 = new User();
	public User u2 = new User();
	
	public Review r1 = new Review();
	public Review r2 = new Review();
	
	public TestUtilities() {
		
		r1 = new Review();
		
		u1.setUsername("u1");
		u1.setPasswordPlain("pass");
		u1.setEmail("a@b.c");
		u1.setFirstName("John");
		u1.setLastName("Doe");
		
		List<Integer> u1f = new ArrayList<>();
		u1f.add(1);
		
		u1.setFavorites(u1f);
		
		u2.setUsername("u2");
		u2.setPasswordPlain("password");
		u2.setEmail("a@a.a");
		u2.setFirstName("Jane");
		u2.setLastName("Doe");
		List<Integer> u2f = new ArrayList<>(u1f);
		u2f.add(2);
		u2.setFavorites(u2f);
		
		r1.setId(1);
		r1.setBrewery(1); r1.setSubmitter(u1); r1.setReviewText("r1");
		r2.setId(2);
		r2.setBrewery(2); r2.setSubmitter(u2); r2.setReviewText("r2");
	}

	public static void clearDB() {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.getTransaction();
		if (tx.isActive()) {
			tx.rollback();
		}
		tx.begin();
		try {
			int n;
			Query query = s.createNativeQuery("TRUNCATE " + HibernateUtil.getCurrentSchema() + ".favorites CASCADE");
			n = query.executeUpdate();
//			System.out.println("DELETE FAVORITES: " + n);
			tx.commit();
			
			tx = s.beginTransaction();
			query = s.createQuery("delete from Review cascade");
			n = query.executeUpdate();
//			System.out.println("DELETE REVIEW: " + n);
			tx.commit();
			
			tx = s.beginTransaction();
			query = s.createQuery("delete from User cascade");
			n = query.executeUpdate();
//			System.out.println("DELETE USER: " + n);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	/**
	 * Populates database with starter data
	 */
	public static void initDB() {
		clearDB();
		
		// init users for front end
		User u1 = new User();
		u1.setUsername("cpbnj");
		u1.setPasswordPlain("abc123");
		u1.setFirstName("Crunchy");
		u1.setLastName("Peanut Butter & Jelly");
		u1.setEmail("ilovecpbnj@yum.com");
		u1.getFavorites().add(100);
		u1.getFavorites().add(1);
		
		UserDAO dao = new UserDAO();
		dao.saveUser(u1);
		
		ReviewDAO rDAO = new ReviewDAO();
		Review rev = new Review();
		rev.setSubmitter(u1);
		rev.setId(1);
		rev.setBrewery(1);
		rev.setRating(9);;
		rev.setReviewText("WOW GREAT BEER");
		rDAO.saveReview(rev);
		
	}
}

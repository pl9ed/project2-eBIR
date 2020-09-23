package com.revature;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.DAO.UserDAO;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.HibernateUtil;

@Repository
public class TestUtilities {
	
	@Autowired
	private static UserService us;
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
	
	public void setupDB() {
		try {
			us.updateUser(u1);
			us.updateUser(u2);
			
//			rs.insertReview(r1);
//			rs.insertReview(r2);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error during setupDB");
		}
	}

	public static void clearDB() {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		try {
			Query query = s.createNativeQuery("DELETE FROM favorites CASCADE");
			query.executeUpdate();
			tx.commit();
			
			tx = s.beginTransaction();
			query = s.createQuery("delete from Review cascade");
			query.executeUpdate();
			tx.commit();
			
			tx = s.beginTransaction();
			query = s.createQuery("delete from User cascade");
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	/**
	 * Populates 
	 */
	public static void initDB() {
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
		dao.saveUser(u1);
	}
}

package com.revature;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.services.ReviewService;
import com.revature.services.UserService;
import com.revature.util.HibernateUtil;

@Repository
public class TestUtilities {
	
	@Autowired
	private static UserService us;
	@Autowired
	private static ReviewService rs;
	
	private static ObjectMapper om = new ObjectMapper();
	
	public User u1 = new User();
	public User u2 = new User();
	
	public Review r1 = new Review();
	public Review r2 = new Review();
	
	public TestUtilities() {
		
		r1 = new Review();
		
		u1.setUsername("u1");
		u1.setPassword("pass");
		u1.setEmail("a@b.c");
		u1.setFirstName("John");
		u1.setLastName("Doe");
		
		List<Integer> u1f = new ArrayList<>();
		u1f.add(1);
		
		u1.setFavorites(u1f);
		
		u2.setUsername("u2");
		u2.setPassword("password");
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
			Query query = s.createNativeQuery("DELETE FROM public.favorites CASCADE");
			query.executeUpdate();
			tx.commit();
			
			tx = s.beginTransaction();
			query = s.createQuery("delete from User cascade");
			query.executeUpdate();
			tx.commit();
			
			tx = s.beginTransaction();
			query = s.createQuery("delete from Review cascade");
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
}

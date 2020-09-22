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
import com.revature.models.Brewery;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.services.BreweryService;
import com.revature.services.ReviewService;
import com.revature.services.UserService;
import com.revature.util.HibernateUtil;

@Repository
public class TestUtilities {
	
	@Autowired
	private static UserService us;
	@Autowired
	private static BreweryService bs;
	@Autowired
	private static ReviewService rs;
	
	private static ObjectMapper om = new ObjectMapper();
	
	public User u1 = new User();
	public User u2 = new User();
	
	public Brewery b1 = new Brewery();
	public Brewery b2 = new Brewery();
	
	public Review r1 = new Review();
	public Review r2 = new Review();
	
	public TestUtilities() {
		
		r1 = new Review();
		b1.setId(1);
		b2.setId(2);
		b1.setName("b1");
		b2.setName("b2");
		
		String b1_s = "{\"id\":1,\"name\":\"5 Rivers Brewing LLC\",\"breweryType\":\"planning\",\"street\":\"\",\"city\":\"Spanish Fort\",\"state\":\"Alabama\",\"postalCode\":\"36527-3161\",\"country\":\"United States\",\"longitude\":\"-87.9152724\",\"latitude\":\"30.6749127\",\"phone\":\"2516897483\",\"websiteUrl\":\"http://5riversbrewing.com\",\"updatedAt\":\"10000\"}";
		String b2_s = "{\"id\":2,\"name\":\"Avondale Brewing Co\",\"breweryType\":\"micro\",\"street\":\"201 41st St S\",\"city\":\"Birmingham\",\"state\":\"Alabama\",\"postalCode\":\"35222-1932\",\"country\":\"United States\",\"longitude\":\"-86.774322\",\"latitude\":\"33.524521\",\"phone\":\"2057775456\",\"websiteUrl\":\"http://www.avondalebrewing.com\",\"updatedAt\":\"1000\"}";
		
		try {
			b1 = om.readValue(b1_s, Brewery.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.out.println("Couldn't map b1");
		}
		try {
			b2 = om.readValue(b2_s, Brewery.class);
		} catch (JsonProcessingException e) {
			System.out.println("Couldn't map b2");
		}
		
		u1.setUsername("u1");
		u1.setPassword("pass");
		u1.setEmail("a@b.c");
		u1.setFirstName("John");
		u1.setLastName("Doe");
		
		List<Brewery> u1f = new ArrayList<>();
		u1f.add(b1);
		
		u1.setFavorites(u1f);
		
		u2.setUsername("u2");
		u2.setPassword("password");
		u2.setEmail("a@a.a");
		u2.setFirstName("Jane");
		u2.setLastName("Doe");
		List<Brewery> u2f = new ArrayList<>(u1f);
		u2f.add(b2);
		u2.setFavorites(u2f);
		
		r1.setId(1);
		r1.setBrewery(b1); r1.setSubmitter(u1); r1.setReviewText("r1");
		r2.setId(2);
		r2.setBrewery(b2); r2.setSubmitter(u2); r2.setReviewText("r2");
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
		try {
			Session s = HibernateUtil.getSession();
			Transaction tx = s.beginTransaction();
			Query query = s.createQuery("delete from User cascade");
			query.executeUpdate();
			tx.commit();
			
			tx = s.beginTransaction();
			query = s.createQuery("delete from Brewery cascade");
			query.executeUpdate();
			tx.commit();
			
			tx = s.beginTransaction();
			query = s.createQuery("delete from Review cascade");
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

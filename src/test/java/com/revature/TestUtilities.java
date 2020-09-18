package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Brewery;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class TestUtilities {
	
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
		
		u1.setUsername("u1");
		u2.setUsername("u2");
		
		r1.setId(1);
		r1.setBrewery(b1); r1.setSubmitter(u1); r1.setReviewText("r1");
		r2.setId(2);
		r2.setBrewery(b2); r2.setSubmitter(u2); r2.setReviewText("r2");
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

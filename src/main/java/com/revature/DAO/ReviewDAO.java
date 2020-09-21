package com.revature.DAO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.models.Brewery;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

@Repository
public class ReviewDAO implements IReviewDAO {
	private Session s;

	public ReviewDAO() {
		s = HibernateUtil.getSession();
	}

	@Override
	public Set<Review> findAll() {
		return new HashSet<Review>(s.createQuery("FROM Review r", Review.class).getResultList());
	}
	
	@Override
	public Set<Review> findBy(Brewery b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveReview(Review review) {
		if (review == null) {
			return false;
		}
		
		Transaction t = s.beginTransaction();
		
		try {
			Serializable ret = s.save(review);
			if (ret != null) {
				t.commit();
				return true;
			} else {
				t.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public Review find(int id) {
		return s.get(Review.class, id);
	}

	@Override
	public boolean updateReview(Review review) {
		if (!(review instanceof Review)) {
			return false;
		}
		
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();

		try {
			Review ret = (Review) s.merge(review);
			tx.commit();
			return ret.equals(review);
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		return false;
	}

	@Override
	public boolean deleteReview(Review review) {
		if (review == null) {
			return false;
		}
		s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(review);
		
		tx.commit();
		return true;
	}




}

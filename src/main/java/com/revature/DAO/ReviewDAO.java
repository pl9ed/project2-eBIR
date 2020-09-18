package com.revature.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.models.Review;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

@Repository
public class ReviewDAO implements IReviewDAO {
	private Session s = HibernateUtil.getSession();

	public ReviewDAO() {}

	@Override
	public List<Review> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Review> findByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveReview(Review review) {
		if (review == null) {
			return false;
		}
		
		Transaction t = s.beginTransaction();
		
		Serializable ret = s.save(review);
		
		if (ret != null) {
			t.commit();
			return true;
		}
		t.rollback();
		return false;
	}

	@Override
	public Review find(Review r) {
		return find(r.getId());
	}

	@Override
	public Review find(int id) {
		return s.get(Review.class, id);
	}

	@Override
	public boolean updateReview(Review review) {
		// TODO Auto-generated method stub
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

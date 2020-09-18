package com.revature.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Review;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class ReviewDAO implements IReviewDAO{
	private Session s;
	
	{
		s = HibernateUtil.getSession();
	}

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
		Transaction t = s.beginTransaction();
		
		Serializable ret = s.save(review);
		t.commit();
		
		return ret != null;
	}

	@Override
	public Review find(Review r) {
		return find(r.getId());
	}

	@Override
	public Review find(int id) {
		return s.get(Review.class, id);
	}
	


}

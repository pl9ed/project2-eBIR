package com.revature.DAO;

import java.util.List;
import java.util.Set;

import com.revature.models.Review;
import com.revature.models.User;

public interface IReviewDAO {

	public List<Review> findAll();
	public Set<Review> findByUser(User user);
	
	public Review find(Review r);
	public Review find(int id);
	
	// return true on success
	public boolean saveReview(Review review);
}

package com.revature.DAO;

import java.util.Set;

import com.revature.models.Brewery;
import com.revature.models.Review;
import com.revature.models.User;

public interface IReviewDAO {

	public Set<Review> findAll();
	public Set<Review> findBy(User user);
	public Set<Review> findBy(Brewery b);
	
	public Review find(int id);
	
	public boolean saveReview(Review review);
	public boolean updateReview(Review review);
	public boolean deleteReview(Review review);
}

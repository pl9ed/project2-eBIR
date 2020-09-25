package com.revature.DAO;

import java.util.List;
import java.util.Set;

import com.revature.models.Review;
import com.revature.models.User;

public interface IReviewDAO {

	public Set<Review> findAll();
	public Set<Review> findByBrewery(int id);
	public Set<Review> findByUser(String username);
	public Set<Review> findByUser(User u);
	
	public Review find(int id);
	
	public boolean saveReview(Review review);
	public boolean updateReview(Review review);
	public boolean deleteReview(Review review);
	
}

package com.revature.DAO;

import java.util.List;

import com.revature.models.Review;
import com.revature.models.User;

public interface IReviewDAO {

	public List<Review> findAll();
	public Review findReview(User user);
	public void setReview(String review);
}

package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;

import com.revature.DAO.IReviewDAO;
import com.revature.models.Review;

@Service
public class ReviewService {
	
	@Autowired
	private IReviewDAO reviewDAO;
	
	public ReviewService() {
		super();
	}
	
	public Review insertReview(Review r) {
		boolean nR = reviewDAO.saveReview(r);
		if(nR) {
			return r;
		}
		return null;
		
	}
	
	// want it to return review object if it deletes successfully
	// null if it fails
	public Review deleteReview(Review r) {
		if(reviewDAO.deleteReview(r)) {
			return r;
		}
		return null;
	}
}

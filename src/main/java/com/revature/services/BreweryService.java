package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.DAO.IBreweryDAO;
import com.revature.DAO.IReviewDAO;
import com.revature.models.Brewery;
import com.revature.models.Review;
import com.revature.models.User;

@Service
public class BreweryService {
	
	@Autowired
	private IReviewDAO rDAO;
	@Autowired
	private IBreweryDAO bDAO;
	
	public boolean addReview(Review r) {
		boolean revSuccess = rDAO.saveReview(r);
		Brewery b = r.getBrewery();
		b.getReviews().add(r);
		boolean brewSuccess = bDAO.updateBrewery(b);
		return (revSuccess && brewSuccess);
	}
	
	public double updateRating(double newRating, Brewery b) {
		b.updateRating(newRating);
		return b.getRating();
	}

}

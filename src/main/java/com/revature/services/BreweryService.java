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
		return rDAO.saveReview(r);
	}
	
	public boolean updateRating(double newRating, Brewery b) {
		if (newRating > 5 || newRating < 0) {
			return false;
		}
		b.updateRating(newRating);
		return bDAO.updateBrewery(b);
	}

}

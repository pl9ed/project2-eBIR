package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.DAO.IBreweryDAO;
import com.revature.DAO.IReviewDAO;
import com.revature.DAO.IUserDAO;
import com.revature.models.Brewery;
import com.revature.models.Review;
import com.revature.models.User;

@Service
public class BreweryService {

	@Autowired
	private IReviewDAO rDAO;
	@Autowired
	private IBreweryDAO bDAO;
	@Autowired
	private IUserDAO uDAO;

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

	public boolean addFavorite(User u, Brewery b) {
		if (u == null || b == null) {
			return false;
		} else if (b.getId() == 0 || b.getName().length() < 1) {
			return false;
		}
		u.getFavorites().add(b);
		return uDAO.updateUser(u);
	}

	public boolean removeFavorite(User u, Brewery b) {
		if (u == null || b == null) {
			return false;
		} else if (b.getId() == 0 || b.getName().length() < 1) {
			return false;
		}
		u.getFavorites().remove(b);
		return uDAO.updateUser(u);
	}
	
	public Set<Review> getReviewsFor(Brewery b) {
		Set<Review> ret = new HashSet<Review>();
		try {
			ret = rDAO.findBy(b);
		} catch (NullPointerException e) {
			// log here
		}
		return ret;
	}

}

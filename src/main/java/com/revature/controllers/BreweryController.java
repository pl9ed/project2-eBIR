package com.revature.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.DAO.IReviewDAO;
import com.revature.models.Brewery;
import com.revature.models.Review;

@Controller
public class BreweryController {
	
	@Autowired
	private IReviewDAO dao;
	
	@PostMapping("review")
	@ResponseBody
	public ResponseEntity<Review> addReview(@RequestBody Review review) {
		if (review != null) {
			if (dao.saveReview(review)) {
				// return same review object for validation in front end
				return ResponseEntity.status(201).body(review);
			}
			
			// problem with saving to DB
			return ResponseEntity.status(409).body(review);
			
		}
		
		// problem with review object
		return ResponseEntity.status(400).build();
	}
	
	@PostMapping("review/get")
	@ResponseBody
	public ResponseEntity<Set<Review>> getReviews(@RequestBody Brewery brewery) {
		if (brewery != null) {
			// return review set to front end
			return ResponseEntity.status(201).body(dao.findBy(brewery));
		} else {
			// problem with brewery object
			return ResponseEntity.status(400).build();
		}
	}
}

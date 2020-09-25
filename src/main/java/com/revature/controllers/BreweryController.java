package com.revature.controllers;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.DAO.IReviewDAO;
import com.revature.models.Review;

@CrossOrigin
@Controller
public class BreweryController {
	private static Logger log = Logger.getLogger(BreweryController.class);

	@Autowired
	private IReviewDAO dao;
	
	@PostMapping("review")
	@ResponseBody
	public ResponseEntity<Review> addReview(@RequestBody Review review) {
		if (review != null) {
			if (dao.saveReview(review)) {
				// return same review object for validation in front end
				log.info("successfully saved to database");
				return ResponseEntity.status(201).body(review);
			}
			
			// problem with saving to DB
			log.error("encountered an error with saving to database");
			return ResponseEntity.status(409).body(review);
			
		}
		
		// problem with review object
		return ResponseEntity.status(400).build();
	}
	
	@GetMapping("brewery/{id}/reviews")
	@ResponseBody
	public ResponseEntity<Set<Review>> getReviews(@PathVariable("id") int id) {
		//System.out.print(id);
		if (id > 0) {
			// return review set to front end
			return ResponseEntity.status(200).body(dao.findByBrewery(id));
		} else {
			// problem with brewery object
			return ResponseEntity.status(400).build();
		}
	}
}

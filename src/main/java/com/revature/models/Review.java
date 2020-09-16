package com.revature.models;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Review {
	
	// user who submitted review
	private User submitter = new User();
	private Brewery brewery = new Brewery();
	private String reviewText = "";
	
	public Review() {}

	public User getSubmitter() {
		return submitter;
	}

	public void setSubmitter(User submitter) {
		this.submitter = submitter;
	}

	public Brewery getBrewery() {
		return brewery;
	}

	public void setBrewery(Brewery brewery) {
		this.brewery = brewery;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	// default toString() changed to print out user's username brewery's id to prevent infinite loops
	@Override
	public String toString() {
		return "Review [submitter=" + submitter.getUsername() + ", brewery=" + brewery.getId() + ", reviewText=" + reviewText + "]";
	}
	
	

}

package com.revature.models;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode @Getter @Setter
public class Review {
	
	@Id
	private int id;
	
	// user who submitted review
	@ManyToOne
	@JoinColumn(name="Submitter", nullable=false)
	private User submitter = new User();
	
	@ManyToOne
	@JoinColumn(name="brewery")
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

package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode @Getter @Setter
public class Review {
	
	@Id
	private int id;
	
	// user who submitted review
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="submitter", nullable=false)
	private User submitter = new User();
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="brewery", nullable=false)
	private Brewery brewery = new Brewery();
	private String reviewText = "";
	
	public Review() {}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

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

package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@EqualsAndHashCode @Getter @Setter @ToString
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_seq_gen")
	@SequenceGenerator(name = "reviews_seq_gen", sequenceName = "reviews_id_seq")
	private int id;
	
	// user who submitted review
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="submitter", nullable=false)
	private User submitter = new User();
	
	@OnDelete(action = OnDeleteAction.CASCADE)
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="brewery", nullable=false)
	private int brewery;
	
	private int rating;
	private String reviewText;
	
	public Review() {
		super();
	}
	
	// override setters for validation
	public void setRating(int rating) {
		if (rating <= 10 && rating >= 1) {
			this.rating = rating;
		}
	}
	
	public void setReviewText(String text) {
		if (text != null && text.length() > 0) {
			this.reviewText = text;
		}
	}
	
	public static Review parseReview(Review r) {
		Review ret = new Review();
		ret.id = r.id;
		ret.submitter = User.parseUser(r.submitter);
		ret.brewery = r.brewery;
		ret.rating = r.rating;
		ret.reviewText = r.reviewText.replace(";", ",");

		return ret;
		
	}
}

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
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_seq_gen")
//	@SequenceGenerator(name = "reviews_seq_gen", sequenceName = "reviews_id_seq")
	@GeneratedValue
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (brewery != other.brewery)
			return false;
		if (id != other.id)
			return false;
		if (rating != other.rating)
			return false;
		if (reviewText == null) {
			if (other.reviewText != null)
				return false;
		} else if (!reviewText.equals(other.reviewText))
			return false;
		if (submitter == null) {
			if (other.submitter != null)
				return false;
		} else if (!submitter.equals(other.submitter))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brewery;
		result = prime * result + id;
		result = prime * result + rating;
		result = prime * result + ((reviewText == null) ? 0 : reviewText.hashCode());
		result = prime * result + ((submitter == null) ? 0 : submitter.hashCode());
		return result;
	}
	
	
	
}

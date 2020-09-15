package com.revature.models;

public class Review {
	
	// user who submitted review
	private String username = "";
	private Brewery brewery = new Brewery();
	private String reviewText = "";
	
	public Review() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brewery == null) ? 0 : brewery.hashCode());
		result = prime * result + ((reviewText == null) ? 0 : reviewText.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
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
		if (brewery == null) {
			if (other.brewery != null)
				return false;
		} else if (!brewery.equals(other.brewery))
			return false;
		if (reviewText == null) {
			if (other.reviewText != null)
				return false;
		} else if (!reviewText.equals(other.reviewText))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
	// default toString() changed to print out brewery's id to prevent infinite loops
	@Override
	public String toString() {
		return "Review [username=" + username + ", brewery=" + brewery.getId() + ", reviewText=" + reviewText + "]";
	}
	
	

}

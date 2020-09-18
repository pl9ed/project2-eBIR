package com.revature.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@EqualsAndHashCode @ToString
public class Brewery {
	
	// fields from API
	@Id
	private int id;
	private String name = "";
	private String breweryType = "";
	private String street = "";
	private String city = "";
	private String state = "";
	private String postalCode = "";
	private String country = "";
	private double longitude;
	private double latitude;
	private String phone = "";
	private String websiteUrl = "";
	
	private long updatedAt;
	
	// custom fields
	private double rating;
	
	// total number of reviews
	private int n = 0;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy="brewery")
	private Set<Review> reviews;
	
	// calculate new rating using cumulative moving average
	public void updateRating(double newRating) {
		n++;
		this.rating = rating + ((newRating - rating) / n);
	}
	
	// constructors, setters/getters
	public Brewery() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreweryType() {
		return breweryType;
	}

	public void setBreweryType(String breweryType) {
		this.breweryType = breweryType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public double getNumberofRatings() {
		return this.n;
	}

}

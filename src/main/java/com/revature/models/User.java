package com.revature.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.mindrot.jbcrypt.BCrypt;

import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;

@Entity
@Table(name="Users")
@EqualsAndHashCode
public class User {
	@Id
	@NotNull
	private String username = "";
	
	private String password = "";
	private String firstName = "";
	private String lastName = "";
	private String email = "";
	
	@OneToMany(mappedBy="name")
	private List<Brewery> favorites = new ArrayList<Brewery>();
	
	public User() {
		
	}
	
	public User(String username, String password, String firstName, String lastName, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName= firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	// hash password
	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	// check password
	public boolean checkPassword(String password) {
		return BCrypt.checkpw(password, this.password);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Brewery> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Brewery> favorites) {
		this.favorites = favorites;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", favorites=" + favorites + "]";
	}

}

package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;

@Component
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
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			joinColumns = 
				@JoinColumn(name = "username"), 
			inverseJoinColumns = 
				@JoinColumn(name = "id"))
	private List<Integer> favorites = new ArrayList<Integer>();
	
	public User() {}
	
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
		if (password != null && password.length() > 0) {
			this.password = BCrypt.hashpw(password, BCrypt.gensalt());
		}
	}
	
	// check password
	public boolean checkPassword(String password) {
		if (password != null && password.length() > 0) {
			return BCrypt.checkpw(password, this.password);
		}
		return false;
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

	public boolean setEmail(String email) {
		if (email != null) {
			String regex = "(.+)[@](.+)[.](.+)";
			if (email.matches(regex)) {
				this.email = email;
				return true;
			}
		}
		return false;
	}

	public List<Integer> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Integer> favorites) {
		this.favorites = favorites;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", favorites=" + favorites + "]";
	}

}

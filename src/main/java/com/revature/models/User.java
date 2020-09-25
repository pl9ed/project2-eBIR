package com.revature.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;

@Component
@Entity
@Table(name="Users")
public class User {
	@Id
	@NotNull
	private String username = "";
	
	private String password = "";
	private String firstName = "";
	private String lastName = "";
	private String email = "";
	
	@ElementCollection
	@CollectionTable(name="favorites",
		joinColumns=
			@JoinColumn(name="username"))
	private List<Integer> favorites = new ArrayList<Integer>();
	
	public User() {}

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
	public void setPasswordPlain(String password) {
		if (password != null && password.length() > 0) {
			this.password = BCrypt.hashpw(password, BCrypt.gensalt());
		}
	}
	
	public void setPassword(String password) {
		this.password = password;
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

	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
//		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		
		if (favorites == null) {
			if (other.favorites != null)
				return false;
		} else if (favorites.size() != other.favorites.size()) {
			return false;
		} else {
			// compare each element I guess? I have no idea why arraylist.equals isn't working
			for (int i = 0; i < favorites.size(); i++) {
				if (!favorites.get(i).equals(other.favorites.get(i))) {
					return false;
				}
			}
		}
		
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} // don't check hashed passwords for equality, just check null
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public static User parseUser(User u) {
		User ret = new User();
		ret.email = u.email.replace(";", "");
		ret.favorites = u.favorites;
		ret.firstName = u.firstName.replace(";", "");
		ret.lastName = u.lastName.replace(";", "");
		ret.password = u.password.replace(";", "");
		ret.username = u.username.replace(";", "");
		return ret;
	}

}

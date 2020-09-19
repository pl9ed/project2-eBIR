package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.DAO.IUserDAO;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;

@Service
public class UserService {
	
	@Autowired
	private IUserDAO userDAO;
	
	public UserService() {
		super();
	}
	
	//creates a new user object with given details and inserts user into DB
	public User register(String username, String password, String firstName, String lastName, String email) {
		User user = new User(username, password, firstName, lastName, email);
		boolean value = userDAO.saveUser(user);
		if (value==false) {
			return null;
		}
		return user;
	}
	
	//for user login, checks if account exists with get() to find username. Compares password input with actual password in DB.
	/**
	 * 
	 * @param username
	 * @param password
	 * @return User object on successful login, null otherwise
	 */
	public User login(String username, String password) {
		User user = null;
		try {
			User temp = userDAO.findByUsername(username);
			
			if (temp.checkPassword(password)) {
				user = temp;
			} 
		} catch(NullPointerException e) {
			throw new ResourceNotFoundException("User with username: " + username + " not found!");
		}
		return user;
	}
	
	//update user first name
	public String updateFirstName(User user, String newFirstname) {
		user.setFirstName(newFirstname);
		try{
			userDAO.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user.getFirstName();
	}
	
	//update user last name
	public String updateLastName(User user, String newLastName) {
		user.setLastName(newLastName);
		try {
			userDAO.updateUser(user);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return user.getLastName();
	}
	
	//update password
	/**
	 * Returns the hashed password
	 * null for exceptions
	*/
	public String updatePassword(User user, String newPassword) {
		user.setPassword(newPassword);
		try {
			userDAO.updateUser(user);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return user.getPassword();
	}
	
	//update email
	public String updateEmail(User user, String newEmail) {
		user.setEmail(newEmail);
		try {
			userDAO.updateUser(user);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return user.getEmail();
	}
	
	
}

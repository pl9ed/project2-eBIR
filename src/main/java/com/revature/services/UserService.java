package com.revature.services;

import com.revature.DAO.IUserDAO;
import com.revature.DAO.UserDAO;
import com.revature.models.User;

public class UserService {
	
	private IUserDAO userDAO;
	
	public UserService() {
		super();
		this.userDAO = new UserDAO();
	}
	
	//creates a new user object with given details and inserts user into DB
	public User register(String username, String password, String firstName, String lastName, String email) {
		User user = new User(username, password, firstName, lastName, email);
		boolean value = userDAO.insert(user);
		if (value==false) {
			return null;
		}
		return user;
	}
	
	//for user login, checks if account exists with get() to find username. Compares password input with actual password in DB.
	public User login(String username, String password) {
		User user= null;
		try {
			user = userDAO.findByUsername(username);

		}catch(NullPointerException e) {
			e.printStackTrace();
			return null;
		}
		
		if (password.equals(user.getPassword())) {
			return user;
		} else {
			System.out.println("Wrong password");
		}
		return null;
	}
	
	//update user first name
	public String updateFirstName(User user, String newFirstname) {
		try{
			userDAO.updateFirstName(user, newFirstname);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("update failed");
			return null;
		}
		return newFirstname;
	}
	
	//update user last name
	public String updateLastName(User user, String newLastName) {
		try {
			userDAO.updateLastName(user, newLastName);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return newLastName;
	}
	
	//update password
	public String updatePassword(User user, String newPassword) {
		try {
			userDAO.updatePassword(user, newPassword);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return newPassword;
	}
	
	//update email
	public String updateEmail(User user, String newEmail) {
		try {
			userDAO.updateEmail(user, newEmail);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return newEmail;
	}
	
}

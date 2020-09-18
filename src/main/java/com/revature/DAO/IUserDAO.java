package com.revature.DAO;

import java.util.List;

import com.revature.models.Brewery;
import com.revature.models.User;

public interface IUserDAO {

	public List<User> findAll();
	public User findUser(String username);
	
	public void setUser(String username, String password, String firstname, String lastname, String email);
	public boolean saveUser(User u);
	
	public boolean updateUser(User u);
	public boolean deleteUser(User u);
	public User findByUsername(String user);
	public boolean insert(User user);
	public void updateFirstName(User user, String newFirstname);
	public void updateLastName(User user, String newFirstname);
	public void updatePassword(User user, String newPassword);
	public void updateEmail(User user, String email);
	public void updateFav(List<Brewery> favorite);
}
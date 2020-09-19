package com.revature.DAO;

import java.util.List;

import com.revature.models.User;

public interface IUserDAO {

	public List<User> findAll();
	public User findUser(String username);
	
	public boolean saveUser(User u);
	
	public boolean updateUser(User u);
	public boolean deleteUser(User u);
	public User findByUsername(String user);
}

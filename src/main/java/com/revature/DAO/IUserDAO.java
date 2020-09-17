package com.revature.DAO;

import java.util.List;

import com.revature.models.User;

public interface IUserDAO {

	public List<User> findAll();
	public User findUser(int id);
	public void setUser(String username, String password, String firstname, String lastname, String email);
}

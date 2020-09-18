package com.revature.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User;
import com.revature.util.HibernateUtil;


public class UserDAO implements IUserDAO{

	//handles user info and the database
	@Override
	public List<User> findAll(){
		List<User> list = null; 
		
		Session s = HibernateUtil.getSession();

		
		return list;
	}

	@Override
	public User findUser(String username) {
		return HibernateUtil.getSession().get(User.class, username);
	}
	
	@Override
	public boolean saveUser(User u) {
		if (u == null) {
			return false;
		}
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		Serializable ret = s.save(u);
		
		if (ret == u.getUsername()) {
			tx.commit();
			return true;
		} else {
			tx.rollback();
			return false;
		}
	}

	@Override
	public void setUser(String username, String password, String firstName, String lastName, String email) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		//user.setFavorites(null);
		
		s.save(user);
		tx.commit();
		
	}

	@Override
	public boolean updateUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User u) {
		if (u == null) {
			return false;
		}
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.delete(u);
		
		tx.commit();
		return true;
	}
}

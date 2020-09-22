package com.revature;

import com.revature.DAO.UserDAO;
import com.revature.models.User;
import com.revature.services.UserService;

public class Driver {

	public static void main(String[] arg) {
		UserService us = new UserService();
		
		us.register("train","toot","Thomas","Engine","ILoveTrains@gmail.com");
		
		User user = us.login("train", "toot");
		
		us.updateFirstName(user, "MC Queen");
		System.out.println(user.toString());
		
		us.register("meme", "god", "dio", "brando", "Jojo@email.com");
		
		User user2 = us.login("meme", "god");
		us.updateLastName(user2,"Stando");
		us.updateEmail(user2, "ZaWorld@gmail.com");
		
		// init users for front end
		User u1 = new User();
		u1.setUsername("cpbnj");
		u1.setPassword("abc123");
		u1.setFirstName("Crunchy");
		u1.setLastName("Peanut Butter & Jelly");
		u1.setEmail("ilovecpbnj@yum.com");
		u1.getFavorites().add(100);
		u1.getFavorites().add(1);
		
		UserDAO dao = new UserDAO();
		dao.saveUser(u1);
		
	}
}

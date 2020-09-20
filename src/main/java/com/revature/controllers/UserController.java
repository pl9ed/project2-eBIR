package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.models.User;
import com.revature.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService us;
	
	@PostMapping("user/register")
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED, reason = "User Created")
	public User register(@RequestBody User u) {
		User user = us.register(u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getEmail());
		return user;
	}
	@PostMapping("user/login")
	@ResponseBody
	public User login(@RequestBody User u) {
		User user = us.login(u.getUsername(), u.getPassword());
		return user;
	}
//	
//	@GetMapping("logout")
//	@ResponseBody
////	public User logout() {
////		/**
////		 * Not sure how to clear a session
//		 * without using HttpSession session
//		 */
//	}
	@PutMapping("user/update_first_name")
	@ResponseBody
	public String updatFirstName(@RequestBody User u) {
		String nFN = us.updateFirstName(u, u.getFirstName());
		return nFN;
	}
	@PutMapping("user/update_last_name")
	@ResponseBody
	public String updateLastName(@RequestBody User u) {
		String nLN = us.updateLastName(u, u.getLastName());
		return nLN;
		
	}
	
	@PutMapping("user/change_password")
	@ResponseBody
	public String updatePassword(@RequestBody User u) {
		String nPw = us.updatePassword(u, u.getPassword());
		return nPw;
	}
}

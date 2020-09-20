package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;
import com.revature.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService us;
	
	@PostMapping("user/register")
	@ResponseBody
	public ResponseEntity<User> register(@RequestBody User u) {
		try {
			User user = us.register(u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getEmail());
			if (user == null) { // user already exists
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} catch (Exception e) {
			// TODO log
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	@PostMapping("user/login")
	@ResponseBody
	public ResponseEntity<User> login(@RequestBody User u) {
		try {
			User user = us.login(u.getUsername(), u.getPassword());
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (ResourceNotFoundException e) {
			// no user with that username
			// TODO log
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		} catch (Exception e) {
			// bad request (incorrect JSON format, null user, etc)
			// TODO log
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
	
	@PutMapping("user/update")
	@ResponseBody
	public ResponseEntity<User> updateUser(@RequestBody User u) {
		// this impl might mean it'd be possible to add a user that doesn't yet exist
		if (us.updateUser(u)) {
			return ResponseEntity.status(HttpStatus.OK).body(u);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	// front end updates user all at once, might not need update method for each field
	@PutMapping("user/update_first_name")
	@ResponseBody
	public String updateFirstName(@RequestBody User u) {
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

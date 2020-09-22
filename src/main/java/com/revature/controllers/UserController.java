package com.revature.controllers;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;
import com.revature.services.UserService;

@Controller
public class UserController {
	private static Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private UserService us;
	
	private static ObjectMapper om = new ObjectMapper();
	
	@PostMapping("user/register")
	@ResponseBody
	public ResponseEntity<User> register(@RequestBody User u) {
		try {
			User user = us.register(u);
			if (user == null) { // user already exists
				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} catch (NonUniqueObjectException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		} catch (Exception e) {
			log.error("encountered an exception");
			log.trace(e,e);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@PostMapping("user/login")
	@ResponseBody
	public ResponseEntity<User> login(HttpEntity<String> login) {
		ObjectNode node;

		try {
			node = om.readValue(login.getBody(), ObjectNode.class);
			String username = node.get("username").textValue();
			String pass = node.get("password").textValue();
			
			if (username != null && pass != null) {
				User user = us.login(username, pass);
				if (user == null) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
				}
				return ResponseEntity.status(HttpStatus.OK).body(user);
			}
		} catch (ResourceNotFoundException e) {
			// no user with that username
			log.error("could not find an user with that username");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		} catch (JsonMappingException e) {
			// incorrect json format
			log.error("encountered an exception: incorrect json format");
		} catch (JsonProcessingException e) {
			log.error("encountered an exception: could not process json");
		} catch (Exception e) {
			log.error("exception encountered");
			log.trace(e,e);
			e.printStackTrace();
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
	
	/*
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
	*/
}

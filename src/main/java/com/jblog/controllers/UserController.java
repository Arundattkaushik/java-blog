package com.jblog.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jblog.entities.User;
import com.jblog.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/blog/user")
public class UserController {
	
	@Autowired
	private UserService uService;

	@PostMapping("/create")
	public ResponseEntity<User> create(@Valid @RequestBody User user) {
		User u = this.uService.createUser(user);
		if (u==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} 
		else {
			return ResponseEntity.of(Optional.of(u));
		}
	}
	
	
	@GetMapping("/get")
	public ResponseEntity<User> getUser(@Valid @RequestParam int uId){
		User u = this.uService.getUserById(uId);
		
		if (u==null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} 
		else {
			return ResponseEntity.of(Optional.of(u));
		}
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<User> updateUserDetails(@Valid @RequestBody User user, @RequestParam int uId){
		User u = this.uService.getUserById(uId);
		
		if (u==null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} 
		else {
			u.setId(uId);
			u.setAbout(user.getAbout());
			u.setEmail(user.getEmail());
			u.setName(user.getName());
			u.setPassword(user.getPassword());
			
			return ResponseEntity.of(Optional.of(this.uService.updateUser(u)));
		}
	}
	
	
	@PostMapping("/delete")
	public ResponseEntity deleteUser(@Valid @RequestParam int uId) {
		uService.deleteUserById(uId);
		return new ResponseEntity(Map.of("msg", "success"), HttpStatus.OK);
	}
	
	
	@GetMapping("/list")
	public ResponseEntity<List<User>> getUserList(){
		List<User> uList = this.uService.getUserList(); 
		return ResponseEntity.of(Optional.of(uList));
	}
}

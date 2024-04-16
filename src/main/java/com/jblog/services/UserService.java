package com.jblog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.entities.User;
import com.jblog.repositories.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo repo;
	
	public User createUser(User user) {
		return repo.save(user);
	}
	
	public User updateUser(User user) {
		return repo.save(user);
	}
	
	public User getUserById(int uId) {
		return repo.findById(uId).get();
	}
	
	public List<User> getUserList(){
		return (List<User>) repo.findAll();
	}
	
	public void deleteUserById(int uId) {
		repo.delete(getUserById(uId));
	}
}

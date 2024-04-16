package com.jblog.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jblog.entities.Category;
import com.jblog.entities.Post;
import com.jblog.entities.User;

import java.util.Date;


public interface PostRepo extends CrudRepository<Post, Integer> {

	List<Post> findByCategory(Category category);
	List<Post> findByAddeDate(Date addeDate);
	List<Post> findByUser(User user);
	List<Post> findByTitle(String title);
	List<Post> findByContent(String content);
}

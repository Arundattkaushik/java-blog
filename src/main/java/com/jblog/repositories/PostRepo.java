package com.jblog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jblog.entities.Category;
import com.jblog.entities.Post;
import com.jblog.entities.User;

import java.util.Date;


public interface PostRepo extends CrudRepository<Post, Integer>, JpaRepository<Post, Integer> {

	List<Post> findByCategory(Category category);
	List<Post> findByAddeDate(Date addeDate);
	List<Post> findByUser(User user);
	
	@Query("select p from Post p where p.title like :keyword ")
	List<Post> searchPostsByTitle(@Param("keyword") String title);
}

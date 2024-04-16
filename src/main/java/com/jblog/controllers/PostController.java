package com.jblog.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jblog.entities.Category;
import com.jblog.entities.Post;
import com.jblog.entities.User;
import com.jblog.services.CategoryService;
import com.jblog.services.PostService;
import com.jblog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/blog/post")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private CategoryService cService;
	@Autowired
	private UserService uService;
	
	@PostMapping("/create")
	public ResponseEntity<Post> addPost(@Valid @RequestBody Post post, 
			@RequestParam int uId,
			@RequestParam int cId
			) 
	{
		User u = this.uService.getUserById(uId);
		Category c = this.cService.getACategoryById(cId);
		if (u== null) {
			return new ResponseEntity(Map.of("msg", "User with this id doesn't exists"), HttpStatus.NOT_FOUND);
		}
		else if (c==null) {
			return new ResponseEntity(Map.of("msg", "Category with this id doesn't exists"), HttpStatus.NOT_FOUND);
		}
		else {
			post.setAddeDate(new Date());
			post.setImage("default.png");
			post.setUser(u);
			post.setCategory(c);
			
			Post p = this.postService.addPost(post);
			if (p==null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			else {
				return ResponseEntity.of(Optional.of(p));
			}
		}
		
	}
	
	
	@PostMapping("/update")
	public ResponseEntity<Post> updatePost(@Valid @RequestBody Post post, @RequestParam int pId){
		Post p = this.postService.getPostById(pId);
		if (p==null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			
			p.setPostUpdateDate(new Date());
			p.setId(pId);
			p.setAddeDate(p.getAddeDate());
			p.setCategory(post.getCategory());
			p.setCategory(post.getCategory());
			p.setContent(post.getContent());
			p.setImage(post.getImage());
			p.setTitle(post.getTitle());
			p.setUser(p.getUser());
			
			return ResponseEntity.of(Optional.of(p));
		}
	}
	
	
	@GetMapping("/get")
	public ResponseEntity<Post> getPostById(@RequestParam int pId){
		Post p = this.postService.getPostById(pId);
		
		if (p==null) {
			return new ResponseEntity(Map.of("msg", "User with this id doesn't exists"), HttpStatus.NOT_FOUND);
		}
		else {
			return ResponseEntity.of(Optional.of(p));
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Post>> getAllPosts(){
		List<Post> pList = this.postService.getAllPosts();
		
		if (pList == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.of(Optional.of(pList));
		}
	}
	
	
	@PostMapping("/delete")
	public ResponseEntity<Map<String, String>> deletePostById(@RequestParam int pId){
		this.postService.deletePostById(pId);
		return new ResponseEntity(Map.of("msg", "success"), HttpStatus.OK);
	}
}

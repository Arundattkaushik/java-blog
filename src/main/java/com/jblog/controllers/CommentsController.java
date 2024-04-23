package com.jblog.controllers;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jblog.entities.Comment;
import com.jblog.entities.Post;
import com.jblog.entities.User;
import com.jblog.services.CommentService;
import com.jblog.services.PostService;
import com.jblog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/blog/comment")
public class CommentsController {
	
	@Autowired
	private CommentService comService;
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public ResponseEntity<Comment> addNewComment
	(
			@Valid
			@RequestBody Comment comment, 
			@RequestParam int pId,
			@RequestParam int uId
			)
	{
		comment.setCreatTime(new Date());
		Post post = this.postService.getPostById(pId);
		User user = this.userService.getUserById(uId);
		if (post==null) {
			return new ResponseEntity(Map.of("msg", "Post with "+pId+" doesn't exists"), HttpStatus.NOT_FOUND);
		}
		
		if (user==null) {
			return new ResponseEntity(Map.of("msg", "User with "+uId+" doesn't exists"), HttpStatus.NOT_FOUND);
		}
		
		comment.setPost(post);
		comment.setUser(user);
		
		Comment com = this.comService.addComment(comment);
		if (com==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} 
		else {
			return ResponseEntity.of(Optional.of(com));
		}
	}
	
	
	@GetMapping("/get")
	public ResponseEntity<Comment> commentById(@RequestParam int cId) {
		Comment c = this.comService.getCommentById(cId);
		System.out.println(c);
		if (c==null) {
			return new ResponseEntity(Map.of("msg", "Comment with "+cId+" doesn't exists"), HttpStatus.NOT_FOUND);
		}
		else {
			return ResponseEntity.of(Optional.of(c));
		}
	}
}

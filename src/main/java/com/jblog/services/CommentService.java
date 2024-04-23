package com.jblog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.entities.Comment;
import com.jblog.repositories.CommentsRepo;

import jakarta.persistence.Id;

@Service
public class CommentService {
	@Autowired
	private CommentsRepo comRepo;
	
	public Comment addComment(Comment comment) {
		Comment com = this.comRepo.save(comment);
		return com;
	}
	
	public Comment getCommentById(int cId) {
		return this.comRepo.getById(cId);
	}

}

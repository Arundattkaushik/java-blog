package com.jblog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.entities.Post;
import com.jblog.repositories.PostRepo;

@Service
public class PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	public Post addPost(Post post) {
		return this.postRepo.save(post);
	}
	
	
	public Post getPostById(int pId) {
		return this.postRepo.findById(pId).get();
	}
	
	public List<Post> getAllPosts(){
		List<Post> pList = (List<Post>) this.postRepo.findAll();
		return pList;
	}
	
	public void deletePostById(int pId) {
		this.postRepo.deleteById(pId);
	}

}

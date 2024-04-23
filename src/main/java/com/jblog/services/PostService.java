package com.jblog.services;

import java.io.File;
import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jblog.entities.Post;
import com.jblog.entities.PostCustomResponse;
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
	
	public PostCustomResponse getAllPosts(int pageSize, int pageNumber, String sortBy, String sortDir){
		
		PostCustomResponse customResponse = new PostCustomResponse();
		Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> page = this.postRepo.findAll(p);
		
		customResponse.setContent(page.getContent());
		customResponse.setPageNumber(page.getNumber());
		customResponse.setPageSize(page.getSize());
		customResponse.setTotalElements(page.getTotalElements());
		customResponse.setTotalPages(page.getTotalPages());
		customResponse.setLastPage(page.isLast());

		return customResponse;
	}
	
	public void deletePostById(int pId) {
		this.postRepo.deleteById(pId);
	}
	
	
	public List<Post> searchPostsByTitle(String title){
		List<Post> posts = this.postRepo.searchPostsByTitle(title);
		return posts;
	}

	
	//Code pending
	public void uploadPostImage() {
		
	}
	
	//Code pending
	public File getPostImage(int pId) {
		return null;	
	}
	
}

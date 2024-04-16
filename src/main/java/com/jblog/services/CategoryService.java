package com.jblog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.entities.Category;
import com.jblog.repositories.CategoryRepo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepo cRepo;
	
	public Category createCategory(Category category) {
		return this.cRepo.save(category);
	}
	
	public Category getACategoryById(int cId) {
		return this.cRepo.findById(cId).get();
	}
	
	public void deleteACategoryById(int cId) {
		this.cRepo.deleteById(cId);
	}
	
	public List<Category> getCatList(){
		return (List<Category>) this.cRepo.findAll();
	}

}

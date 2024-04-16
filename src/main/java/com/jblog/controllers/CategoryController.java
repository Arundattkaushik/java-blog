package com.jblog.controllers;

import java.util.List;
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

import com.jblog.entities.Category;
import com.jblog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/blog/cat")
public class CategoryController {
	@Autowired
	private CategoryService cService;
	
	@PostMapping("/create")
	public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
		
		Category c = this.cService.createCategory(category);
		if (c==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} 
		else {
			return ResponseEntity.of(Optional.of(c));
		}
	}
	
	
	@PostMapping("/update")
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category, @RequestParam int cId){
		category.setId(cId);
		return ResponseEntity.of(Optional.of(this.cService.createCategory(category)));
	}
	
	
	@GetMapping("/get")
	public ResponseEntity<Category> getACategoryById(@RequestParam int cId){
		return ResponseEntity.of(Optional.of(this.cService.getACategoryById(cId)));
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> cList = this.cService.getCatList();
		return ResponseEntity.of(Optional.of(cList));			
	}
	
	
	@PostMapping("/delete")
	public ResponseEntity deleteCategoryById(@RequestParam int cId) {
		this.cService.deleteACategoryById(cId);
		return new ResponseEntity(Map.of("msg", "success"), HttpStatus.OK);
	}
}

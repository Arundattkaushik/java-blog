package com.jblog.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jblog.entities.Category;

public interface CategoryRepo extends CrudRepository<Category, Integer> {

}

package com.jblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.jblog.entities.Comment;

public interface CommentsRepo extends CrudRepository<Comment, Integer>, JpaRepository<Comment, Integer> {

}

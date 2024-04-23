package com.jblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.jblog.repositories.CategoryRepo;
import com.jblog.repositories.CommentsRepo;
import com.jblog.repositories.PostRepo;
import com.jblog.repositories.UserRepo;

@SpringBootApplication
public class JavaBlogsApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(JavaBlogsApplication.class, args);
		
		UserRepo repo = context.getBean(UserRepo.class);
		CategoryRepo cRepo = context.getBean(CategoryRepo.class);
		PostRepo postRepo = context.getBean(PostRepo.class);
		CommentsRepo comRepo = context.getBean(CommentsRepo.class);
	}
}

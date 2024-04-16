package com.jblog.repositories;

import org.springframework.data.repository.CrudRepository;
import com.jblog.entities.User;

public interface UserRepo extends CrudRepository<User, Integer> {

}

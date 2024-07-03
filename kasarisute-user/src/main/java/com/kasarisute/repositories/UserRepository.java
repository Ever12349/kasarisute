package com.kasarisute.repositories;

import org.springframework.data.repository.CrudRepository;

import com.kasarisute.domain.User;


public interface UserRepository extends CrudRepository<User,Long> {

}

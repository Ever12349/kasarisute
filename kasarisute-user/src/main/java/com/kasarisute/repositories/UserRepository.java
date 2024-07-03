package com.kasarisute.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kasarisute.domain.User;


public interface UserRepository extends CrudRepository<User,Long>,PagingAndSortingRepository<User,Long> {

    boolean existsByMail(String mail);
}

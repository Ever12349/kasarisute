package com.kasarisute.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kasarisute.entitys.User;


public interface UserRepository
        extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

    boolean existsByMail(String mail);

    User findByMail(String mail);

    User findByUid(Long uid);

    // User findById(Long id);
}


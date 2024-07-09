package com.kasarisute.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kasarisute.domain.User;


public interface UserRepository
        extends JpaRepository<User, Long> {

    boolean existsByMail(String mail);

    User findByMail(String mail);

    User findByUid(String uid);

    // User findById(Long id);
}

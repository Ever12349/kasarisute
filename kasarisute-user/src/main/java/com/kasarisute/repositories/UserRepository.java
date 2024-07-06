package com.kasarisute.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kasarisute.domain.User;

public interface UserRepository
        extends JpaRepository<User, Long> {

    boolean existsByMail(String mail);

    User findByMail(String mail);

    // User findById(Long id);
}

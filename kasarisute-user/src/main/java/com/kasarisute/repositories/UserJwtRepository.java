package com.kasarisute.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kasarisute.entitys.UserJwt;


public interface UserJwtRepository  extends JpaRepository<UserJwt,Long>{
    UserJwt findOneByUserCode(Long userCode);
}

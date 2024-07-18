package com.kasarisute.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kasarisute.entitys.UserId;


public interface UserIdRepository extends CrudRepository<UserId, Long>, PagingAndSortingRepository<UserId, Long> {
}

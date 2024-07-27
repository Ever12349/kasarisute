package com.kasarisute.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kasarisute.entitys.Task;

public interface TaskRepositority extends JpaRepository<Task, Long> {

}
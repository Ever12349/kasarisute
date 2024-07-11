package com.kasarisute.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kasarisute.domain.SampleTable;

public interface SampleRepository extends JpaRepository<SampleTable,Long> {

}

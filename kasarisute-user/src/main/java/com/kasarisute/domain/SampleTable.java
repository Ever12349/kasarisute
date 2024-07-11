package com.kasarisute.domain;

import com.kasarisute.sequenceGenerator.UserIdGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sample_table")
public class SampleTable {
    @Id
    @UserIdGenerator(dataCenterId = 1,machineId = 2)
    // @GeneratedValue
    // @UuidGenerator
    // UUID id;
    Long id;
    private String title;
}

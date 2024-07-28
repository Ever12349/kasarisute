package com.kasarisute.entitys;

import com.kasarisute.sequenceGenerator.annotations.SnowFlakeGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "task_list_info")
@EqualsAndHashCode(callSuper = true)
public class Task extends TableBaseEntity {
    @Id
    @Column(name = "task_id")
    @SnowFlakeGenerator
    private Long id;

    @Column(name="task_name")
    private String taskName;

}

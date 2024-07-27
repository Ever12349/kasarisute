package com.kasarisute.entitys;

import com.kasarisute.entitys.genericGenerator.TaskIdGenerate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "task_list_info")
public class Task {
    @Id
    @Column(name = "task_id")
    @TaskIdGenerate
    private Long id;

    @Column(name="task_name")
    private String taskName;
    // @Id
    // @UuidGenerator
    // private UUID taskUuid;
}

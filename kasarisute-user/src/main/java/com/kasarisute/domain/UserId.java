package com.kasarisute.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "userid_info")
public class UserId {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "uid", unique = true, nullable = false)
    private String uid;
    @Column(name = "user_code", nullable = false)
    private String userCode;
}

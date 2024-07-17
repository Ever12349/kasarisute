package com.kasarisute.domain;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_jwt")
public class UserJwt {
    @Id
    @Column(name = "user_code", unique = true, length = 16)
    @Comment("用户ID")
    private Long userCode;

    @Column(name="jwt", length=512)
    private String jwt;

}

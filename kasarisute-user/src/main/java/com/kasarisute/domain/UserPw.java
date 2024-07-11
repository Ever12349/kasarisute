package com.kasarisute.domain;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "user_pw")
public class UserPw {
    @Id
    // @GeneratedValue
    // private Long id;

    @Column(name = "user_code", unique = true, length = 16)
    @Comment("用户ID")
    private Long userCode;

    @Column(name = "password", length = 64)
    private String password;
    @Transient
    private boolean pwIsCrypt = false;

}

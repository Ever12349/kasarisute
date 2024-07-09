package com.kasarisute.domain;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "user_pw")
public class UserPw {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Comment("Id")
    // @Column(name = "id", nullable = false)
    // @ColumnDefault("1L")
    // private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("ID")
    @Column(name = "pwId")
    private Long pw_id;

    @Column(name = "uid", unique = true, length = 16)
    @Comment("用户ID")
    private String uid;


    // @Column(name = "user_code", unique = true, length = 16)
    // @Comment("用户的code")
    // private String userCode;

    @Column(name = "password", length = 64)
    private String password;
    @Transient
    private boolean pwIsCrypt = false;

}

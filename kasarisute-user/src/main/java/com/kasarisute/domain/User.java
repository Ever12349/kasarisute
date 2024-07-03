package com.kasarisute.domain;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
@Table(name="user_info")
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="uid",nullable = false)
    private Integer uid;
    @Column(name="user_code",nullable = false,length = 16)
    private String userCode;
    @Column(name="user_name",nullable = true,length = 16)
    private String userName;
    @Column(name="mail",nullable = true,length = 32)
    private String mail;
    @Column(name="permission",nullable = true)
    private Integer permission;
    @Column(name="update_time",nullable = true)
    private String updateTime;
    @Column(name="create_time",nullable = true)
    private String createTime;
    @Column(name="status",nullable = true)
    @ColumnDefault("100")
    private Integer status;
}

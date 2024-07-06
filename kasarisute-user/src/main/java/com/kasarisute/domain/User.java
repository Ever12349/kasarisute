package com.kasarisute.domain;

import java.time.Instant;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_info")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("Id")
    @Column(name = "id",nullable = false,unique = true)
    @ColumnDefault("1L")
    private Long id;

    @Column(name = "uid", unique = true)
    @Comment("Id")
    private String uid;

    @Column(name = "user_code", unique = true, length = 16)
    @Comment("用户的code")
    private String userCode;

    @Column(name = "user_name", nullable = true, length = 16)
    @Comment("用户的名称")
    private String userName;

    @Column(name = "password", nullable = false, length = 16)
    @Comment("用户的密码")
    private String password;
    @Column(name = "mail", nullable = true, length = 32)
    @Comment("用户的邮箱")
    private String mail;

    @Column(name = "permission", nullable = true)
    @Comment("用户的权限 默认值为5")
    @ColumnDefault("5")
    @Value("5")
    private Integer permission = 5;

    @Column(name = "update_time", nullable = true)
    @Comment("更新时间")
    @LastModifiedDate
    private Instant updateTime;

    @Column(name = "create_time", nullable = true)
    @Comment("创建时间")
    @CreatedDate
    private Instant createTime;

    @Column(name = "status", nullable = true)
    @Comment("状态 100未验证,200正常,300异常,400已经注销")
    @ColumnDefault("100")
    @Value("100")
    private Integer status =100;


    
}

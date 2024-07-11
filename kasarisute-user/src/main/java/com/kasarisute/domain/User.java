package com.kasarisute.domain;

import java.time.Instant;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kasarisute.sequenceGenerator.UserIdGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "user_info")
@EntityListeners(AuditingEntityListener.class)
// @DynamicInsert
public class User {
    @Id
    @UserIdGenerator(dataCenterId = 1, machineId = 2)
    @Column(name = "uid", unique = true)
    private Long uid;

    @Column(name = "user_code", insertable = false, updatable = false, unique = true, length = 16)
    private Long UserCode;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_code", referencedColumnName = "user_code")
    private UserPw userPw;

    @Column(name = "user_name", nullable = true, length = 16)
    @Comment("用户的名称")
    private String userName;

    @Column(name = "mail", nullable = true, length = 32)
    @Comment("用户的邮箱")
    private String mail;

    @Column(name = "permission", nullable = true)
    @Comment("用户的权限 默认值为5")
    @ColumnDefault("5")
    @Value("5")
    private Integer permission = 5;

    @Column(name = "roles", nullable = false, length = 64)
    @Comment("用户的角色")
    @ColumnDefault("'USER'")
    @Value("'USER'")
    private String roles = "USER";

    @Column(name = "update_time", nullable = true)
    @Comment("更新时间")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Instant updateTime;

    @Column(name = "create_time", nullable = true)
    @Comment("创建时间")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Instant createTime;

    @Column(name = "status", nullable = true)
    @Comment("状态 100未验证,200正常,300异常,400已经注销")
    @ColumnDefault("100")
    @Value("100")
    private Integer status = 100;
}

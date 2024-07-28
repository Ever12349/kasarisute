package com.kasarisute.entitys;

import java.time.Instant;

import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TableBaseEntity {

    @CreatedBy
    @Comment("创建人的Id")
    protected Long createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("创建日期")
    protected Instant createdDate;

    @LastModifiedBy
    @Comment("最后一次更新用户的Id")
    protected Long updataBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("最后更新时间")
    protected Instant updataDate;
}

package com.kasarisute.entitys;

import java.time.Instant;

import org.hibernate.annotations.Comment;

import com.kasarisute.sequenceGenerator.annotations.SnowFlakeGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "flux_entry_info")
@EqualsAndHashCode(callSuper = true)
public class FluxEntry extends BaseEntity {
    @Id
    @Column(name = "entry_id")
    // @UuidGenerator
    @SnowFlakeGenerator
    private Long id;

    @Column(name = "entry_type")
    @Comment("账目类型 income收入 expense支出")
    private String type;

    @Column(name = "entry_desc")
    @Comment("账目的说明")
    private String description;

    @Column(name = "entry_amountNum")
    @Comment("账目的计算数值")
    private Integer amountNum;

    @Column(name = "entry_amount")
    @Comment("账目的基本数值")
    private Float amount;

    @Column(name = "entry_category")
    @Comment("账目的种类")
    private String category;

    @Column(name = "entry_date")
    @Comment("账目的时间")
    private Instant time;
}

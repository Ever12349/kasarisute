package com.kasarisute.entitys;

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

    @Column(name = "entry_name")
    private String title;

    @Column(name = "entry_amount")
    private String amount;

    @Column(name = "entry_category")
    private String category;
}

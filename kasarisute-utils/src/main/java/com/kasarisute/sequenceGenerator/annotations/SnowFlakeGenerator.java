package com.kasarisute.sequenceGenerator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.ValueGenerationType;

@IdGeneratorType(com.kasarisute.sequenceGenerator.SnowFlakeGenerator.class)
@ValueGenerationType(generatedBy = com.kasarisute.sequenceGenerator.SnowFlakeGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })

public @interface SnowFlakeGenerator {
    public long dataCenterId() default 0L;

    public long machineId() default 0L;

}

package com.kasarisute.sequenceGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.ValueGenerationType;

// @IdGeneratorType(org.hibernate.id.uuid.UuidGenerator.class)
// @ValueGenerationType(generatedBy = org.hibernate.id.uuid.UuidGenerator.class)
@IdGeneratorType(UserIdGeneratorV1.class)
@ValueGenerationType(generatedBy = UserIdGeneratorV1.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface UserIdGenerator {
    public long dataCenterId() default 0L;

    public long machineId() default 0L;
}

package com.kasarisute.sequenceGenerator;

import java.lang.reflect.Member;
import java.util.EnumSet;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.EventTypeSets;
import org.hibernate.generator.GeneratorCreationContext;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;

import utils.SnowFlake;

class Generator<T> {
    private T generatorValue;

    public T getGeneratorValue() {
        return generatorValue;
    }

    public void setGeneratorValue(T generatorValue) {
        this.generatorValue = generatorValue;
    }
    
}

public class UserIdGeneratorV1 implements BeforeExecutionGenerator {

    @SuppressWarnings("rawtypes")
    private Generator generator;

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EventTypeSets.INSERT_ONLY;
    }

    @SuppressWarnings("unchecked")
    private UserIdGeneratorV1(UserIdGenerator config, Member idMember) {
        generator = new Generator<SnowFlake>();
        
        SnowFlake snowFlake = new SnowFlake(config.dataCenterId(), config.machineId());
        
        generator.setGeneratorValue(snowFlake);
    }

    public UserIdGeneratorV1(UserIdGenerator config, Member idMember,
            CustomIdGeneratorCreationContext creationContext) {
        this(config, idMember);
    }

    public UserIdGeneratorV1(UserIdGenerator config, Member member,
            GeneratorCreationContext creationContext) {
        this(config, member);
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        SnowFlake generatorValue = (SnowFlake) this.generator.getGeneratorValue();
        return generatorValue.nextId();
    }

}

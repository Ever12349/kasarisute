package com.kasarisute.sequenceGenerator;

import java.lang.reflect.Member;
import java.util.EnumSet;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.EventTypeSets;
import org.hibernate.generator.GeneratorCreationContext;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;

import com.kasarisute.utils.SnowFlake;

public class SnowFlakeGenerator implements BeforeExecutionGenerator {

    private ValueGenerator<SnowFlake> generator;

    private SnowFlakeGenerator(com.kasarisute.sequenceGenerator.annotations.SnowFlakeGenerator config,
            Member idMember) {

        SnowFlake snowFlake = new SnowFlake(config.dataCenterId(), config.machineId());

        generator = new ValueGenerator<>(snowFlake);

    }

    public SnowFlakeGenerator(com.kasarisute.sequenceGenerator.annotations.SnowFlakeGenerator config, Member idMember,
            CustomIdGeneratorCreationContext creationContext) {
        this(config, idMember);

    }

    public SnowFlakeGenerator(com.kasarisute.sequenceGenerator.annotations.SnowFlakeGenerator config, Member member,
            GeneratorCreationContext creationContext) {
        this(config, member);
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EventTypeSets.INSERT_ONLY;
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        return this.generator.getGeneratorValue().nextId();
    }

}

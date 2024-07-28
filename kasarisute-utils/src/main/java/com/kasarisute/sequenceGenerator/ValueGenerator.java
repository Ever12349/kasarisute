package com.kasarisute.sequenceGenerator;

public class ValueGenerator<T> {
    private T generatorValue;

    public ValueGenerator(T generator) {
       this.generatorValue = generator;
    }

    public ValueGenerator() {}

    public T getGeneratorValue() {
        return generatorValue;
    }

    public void setGeneratorValue(T generatorValue) {
        this.generatorValue = generatorValue;
    }

    
}

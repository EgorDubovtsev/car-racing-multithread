package org.race.util;

import java.util.Map;

public class CarInResultTableEntry<Car,Long> implements Map.Entry<Car,Long > {
    private Car key;
    private Long value;

    public CarInResultTableEntry(Car key, Long value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Car getKey() {
        return key;
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public Long setValue(Long value) {
        return this.value=value;
    }
}

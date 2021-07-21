package org.race.entity;

import org.race.util.CarType;

public class Car {
    private String name;
    private int speedKmPerHour;
    private CarType type;
    private int chanceOfWheelDamageInPercents;

    public Car(String name, int speedKmPerHour, CarType type, int chanceOfWheelDamageInPercents) {
        this.name = name;
        this.speedKmPerHour = speedKmPerHour;
        this.type = type;
        this.chanceOfWheelDamageInPercents = chanceOfWheelDamageInPercents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeedKmPerHour() {
        return speedKmPerHour;
    }

    public void setSpeedKmPerHour(int speedKmPerHour) {
        this.speedKmPerHour = speedKmPerHour;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public int getChanceOfWheelDamageInPercents() {
        return chanceOfWheelDamageInPercents;
    }

    public void setChanceOfWheelDamageInPercents(int chanceOfWheelDamageInPercents) {
        this.chanceOfWheelDamageInPercents = chanceOfWheelDamageInPercents;
    }
}

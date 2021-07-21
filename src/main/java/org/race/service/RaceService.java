package org.race.service;

import org.race.entity.Car;

import java.util.Map;

public interface RaceService {
    Map<Car,Long> startRace(Car... cars);
}

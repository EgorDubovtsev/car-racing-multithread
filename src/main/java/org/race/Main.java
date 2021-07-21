package org.race;

import org.race.entity.Car;
import org.race.service.SimpleCarManagerService;
import org.race.service.SimpleRaceService;
import org.race.util.CarType;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SimpleRaceService simpleRaceService = new SimpleRaceService(new SimpleCarManagerService(), 1000);

        Car car = new Car("1", 100, CarType.TRUCK, 98);
        Car car2 = new Car("2", 110, CarType.SEDAN, 2);
        Car car3 = new Car("3", 180, CarType.BUS, 10);

        Map<Car, Long> resultsTable = simpleRaceService.startRace(car, car2, car3);
        System.out.println("WINNERS:");
        resultsTable.entrySet().forEach(entry -> System.out.println("Racer " + entry.getKey().getName() + " finished at time " + entry.getValue() + " s."));

    }
}

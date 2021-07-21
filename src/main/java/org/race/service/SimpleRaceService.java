package org.race.service;

import org.race.entity.Car;
import org.race.thread.CarLauncher;
import org.race.util.CarInResultTableEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SimpleRaceService implements RaceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleRaceService.class);
    private CarManagerService carManagerService;
    private int distanceInMeters;

    public SimpleRaceService(SimpleCarManagerService carManagerService, int distanceInMeters) {
        this.carManagerService = carManagerService;
        this.distanceInMeters = distanceInMeters;
    }

    public Map<Car, Long> startRace(Car... cars) {
        ExecutorService executorService = Executors.newFixedThreadPool(cars.length);
        Map<Car, Future<Long>> racersAndResults = new LinkedHashMap<>();

        LOGGER.info("{} cars in race", cars.length);

        for (Car car : cars) {
            Future<Long> raceTime = executorService.submit(new CarLauncher(car, carManagerService, distanceInMeters));
            racersAndResults.put(car, raceTime);

        }
        LinkedHashMap<Car, Long> tableOfWinners = new LinkedHashMap<>();

        racersAndResults
                .entrySet()
                .stream()
                .map(entry -> {
                    try {
                        LOGGER.info("{} finish at the time {}", entry.getKey(), entry.getValue().get());

                        return new CarInResultTableEntry<>(entry.getKey(), entry.getValue().get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .sorted(Map.Entry.comparingByValue())
                .filter(Objects::nonNull)
                .forEach(entry -> tableOfWinners.put(entry.getKey(), entry.getValue()));


        return tableOfWinners;
    }
}

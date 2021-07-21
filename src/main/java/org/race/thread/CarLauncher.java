package org.race.thread;

import org.race.entity.Car;
import org.race.service.CarManagerService;
import org.race.service.SimpleCarManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;


public class CarLauncher implements Callable<Long> {
    private Car carForLaunch;
    private CarManagerService simpleCarManagerService;
    private double distanceInMeters;
    private static final Logger LOGGER = LoggerFactory.getLogger(CarLauncher.class);

    public CarLauncher(Car carForLaunch, CarManagerService simpleCarManagerService, double distanceInMeters) {
        this.carForLaunch = carForLaunch;
        this.simpleCarManagerService = simpleCarManagerService;
        this.distanceInMeters = distanceInMeters;
    }

    @Override
    public Long call() throws Exception {
        LOGGER.info("Car launcher for {} is started", carForLaunch.getName());

        return simpleCarManagerService.getDriveTime(distanceInMeters, carForLaunch);
    }
}

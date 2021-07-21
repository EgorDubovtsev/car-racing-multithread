package org.race.service;

import org.race.entity.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleCarManagerService implements CarManagerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCarManagerService.class);
    private static final int WHEEL_REPAIRING_TIME_MS = 5000;

    public long getDriveTime(double distanceInMeters, Car car) {
        double speedInMetersPerSecond = transformKmPerHourToMetersInSecond(car.getSpeedKmPerHour());

        LOGGER.info("{} started race", car.getName());
        LocalTime startTime = LocalTime.now();

        if (isWheelGetDamaged(car.getChanceOfWheelDamageInPercents())){
            LOGGER.info("WHEEL OF {} GET DAMAGED, REPAIRING",car.getName());
            try {
                Thread.sleep(WHEEL_REPAIRING_TIME_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("WHEEL OF {}  REPAIRED",car.getName());
        }


        while (distanceInMeters / speedInMetersPerSecond > 0) {
            distanceInMeters = distanceInMeters - speedInMetersPerSecond;
            LOGGER.info("{} left to drive {} meters", car.getName(), distanceInMeters);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        LocalTime finishTime = LocalTime.now();
        LOGGER.info("{} finished race", car.getName());

        return ChronoUnit.SECONDS.between(startTime, finishTime);
    }

    private double transformKmPerHourToMetersInSecond(int kmPerHour) {
        return kmPerHour / 3.6;
    }

    private boolean isWheelGetDamaged(int damageChance) {
        return damageChance > ThreadLocalRandom.current().nextInt(100);
    }

}

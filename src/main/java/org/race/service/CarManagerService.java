package org.race.service;

import org.race.entity.Car;

public interface CarManagerService {
    long getDriveTime(double distanceInMeters, Car car);
}

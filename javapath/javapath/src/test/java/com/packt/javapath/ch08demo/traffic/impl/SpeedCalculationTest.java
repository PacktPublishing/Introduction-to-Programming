package com.packt.javapath.ch08demo.traffic.impl;

import com.packt.javapath.ch08demo.traffic.Car;
import com.packt.javapath.ch08demo.traffic.Truck;
import com.packt.javapath.ch08demo.traffic.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeedCalculationTest {
    @Test
    void speedCalculation() {
        double timeSec = 5;
        Vehicle vehicle = new CarImpl(2, 2000, 150);
        assertEquals(83.0, vehicle.getSpeedMph(timeSec));
        ((Car) vehicle).setPassengersCount(0);
        assertEquals(91.0, vehicle.getSpeedMph(timeSec));

        vehicle = new TruckImpl(500, 3000, 300);
        assertEquals(98.0, vehicle.getSpeedMph(timeSec));
        ((Truck) vehicle).setPayloadPounds(0);
        assertEquals(105.0, vehicle.getSpeedMph(timeSec));
    }
}
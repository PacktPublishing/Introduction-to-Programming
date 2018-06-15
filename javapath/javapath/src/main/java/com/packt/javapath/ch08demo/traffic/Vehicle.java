package com.packt.javapath.ch08demo.traffic;

import com.packt.javapath.ch08demo.traffic.impl.TrafficFactory;

import java.time.Month;
import java.util.List;

public interface Vehicle {
    double getSpeedMph(double timeSec);

    static List<Vehicle> getTraffic(int vehiclesCount){
        return TrafficFactory.get(vehiclesCount);
    }

    static List<Vehicle> getTraffic(int vehiclesCount, Month month, int dayOfMonth, int hour){
        return TrafficFactory.get(vehiclesCount, month, dayOfMonth, hour);
    }

}

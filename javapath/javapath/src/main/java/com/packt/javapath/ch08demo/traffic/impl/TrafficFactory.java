package com.packt.javapath.ch08demo.traffic.impl;

import com.packt.javapath.ch08demo.speedmodel.impl.SpeedModelFactory;
import com.packt.javapath.ch08demo.speedmodel.SpeedModel;
import com.packt.javapath.ch08demo.traffic.Vehicle;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TrafficFactory {
    public static List<Vehicle> get(int vehiclesCount, Month month, int dayOfMonth, int hour) {
        SpeedModel speedModel = SpeedModelFactory.speedModel(month, dayOfMonth, hour);
        List<Vehicle> list = new ArrayList();
        for (int i = 0; i < vehiclesCount; i++) {
            Vehicle vehicle;
            if (Math.random() <= 0.5) {
                vehicle = new CarImpl(gen(4, 1), gen(4000, 2000), gen(300, 100));
            } else {
                vehicle = new TruckImpl(gen(4000, 1000), gen(6000, 3000), gen(500, 200));
            }
            ((VehicleImpl)vehicle).setSpeedModel(speedModel);
            list.add(vehicle);
        }
        return list;
    }

    public static List<Vehicle> get(int vehiclesCount) {
        List<Vehicle> list = new ArrayList();
        for (int i = 0; i < vehiclesCount; i++) {
            Vehicle vehicle;
            if (Math.random() <= 0.5) {
                vehicle = new CarImpl(gen(4, 1), gen(4000, 2000), gen(300, 100));
            } else {
                vehicle = new TruckImpl(gen(4000, 1000), gen(6000, 3000), gen(500, 200));
            }
            list.add(vehicle);
        }
        return list;
    }

    private static int gen(int i1, int i2) {
        double r = Math.random();
        return (int) Math.rint(r * i1) + i2;
    }

    public static List<Vehicle> get2(int vehiclesCount) {
        List<Vehicle> list = new ArrayList();
        for (int i = 0; i < vehiclesCount; i++){
            Vehicle vehicle;
            if (Math.random() <= 0.5) {
                vehicle = new CarImpl(2, 2000, 150);
            } else {
                vehicle = new TruckImpl(500, 3000, 300);
            }
            list.add(vehicle);
        }
        return list;
    }

    public static List<Vehicle> get1(int vehiclesCount) {
        List<Vehicle> list = new ArrayList();
        return list;
    }
}
package com.packt.javapath.ch08demo;

import com.packt.javapath.ch08demo.traffic.Car;
import com.packt.javapath.ch08demo.traffic.Truck;
import com.packt.javapath.ch08demo.traffic.Vehicle;

import java.time.Month;
import java.util.List;

public class TrafficApp {
    public static void main(String... args){
        System.out.println();
        double timeSec = 5;
        int vehiclesCount = 4;
        List<Vehicle> traffic = Vehicle.getTraffic(vehiclesCount, Month.APRIL, 22, 13);
        for(Vehicle vehicle: traffic){
            System.out.println("Loaded: " + vehicle.getSpeedMph(timeSec));
            if(vehicle instanceof Car){
                ((Car) vehicle).setPassengersCount(0);
                System.out.println("Car(no load): " + vehicle.getSpeedMph(timeSec));
            } else {
                ((Truck) vehicle).setPayloadPounds(0);
                System.out.println("Truck(no load): " + vehicle.getSpeedMph(timeSec));
            }
        }
    }

    public static void main1(String... args){
        System.out.println();
        double timeSec = 5;
        int vehiclesCount = 4;
        List<Vehicle> traffic = Vehicle.getTraffic(vehiclesCount);
        for(Vehicle vehicle: traffic){
            System.out.println("Loaded: " + vehicle.getSpeedMph(timeSec));
            if(vehicle instanceof Car){
                ((Car) vehicle).setPassengersCount(0);
                System.out.println("Car(no load): " + vehicle.getSpeedMph(timeSec));
            } else {
                ((Truck) vehicle).setPayloadPounds(0);
                System.out.println("Truck(no load): " + vehicle.getSpeedMph(timeSec));
            }
        }
    }
}

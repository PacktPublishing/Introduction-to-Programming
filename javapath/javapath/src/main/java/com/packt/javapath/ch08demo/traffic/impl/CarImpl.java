package com.packt.javapath.ch08demo.traffic.impl;

import com.packt.javapath.ch08demo.traffic.Car;

//Notice, that the class access is default
//Only other package members and access this class
class CarImpl extends VehicleImpl implements Car {
    private int passengersCount;
    CarImpl(int passengersCount, int weightPounds, int horsePower) {
        super(weightPounds , horsePower);
        this.passengersCount = passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }

    private int getCarWeightPounds(){ return this.passengersCount * 200 + super.getWeightPounds(); }
    public double getSpeedMph(double timeSec){
        return getSpeedMph(timeSec, this.getCarWeightPounds());
    }
}

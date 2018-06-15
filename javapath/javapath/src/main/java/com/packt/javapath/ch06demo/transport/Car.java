package com.packt.javapath.ch06demo.transport;

public class Car extends Vehicle {
    private int passengersCount;
    public Car(int passengersCount, int weightPounds, int horsePower) {
        super(weightPounds , horsePower);
        this.passengersCount = passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }

    protected int getWeightPounds(){ return this.passengersCount * 200 + super.getWeightPounds(); }
    public double getSpeedMph(double timeSec){
        return getSpeedMph(timeSec, this.getWeightPounds());
    }
}

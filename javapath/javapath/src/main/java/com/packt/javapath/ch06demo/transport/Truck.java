package com.packt.javapath.ch06demo.transport;

public class Truck extends Vehicle {
    private int payloadPounds;
    public Truck(int payloadPounds, int weightPounds, int horsePower) {
        super(weightPounds, horsePower);
        this.payloadPounds = payloadPounds;
    }

    public void setPayloadPounds(int payloadPounds) {
        this.payloadPounds = payloadPounds;
    }

    protected int getWeightPounds(){ return this.payloadPounds + super.getWeightPounds(); }
    public double getSpeedMph(double timeSec){
        return getSpeedMph(timeSec, this.getWeightPounds());
    }
}

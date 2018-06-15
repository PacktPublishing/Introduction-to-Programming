package com.packt.javapath.ch08demo.traffic.impl;

import com.packt.javapath.ch08demo.traffic.Truck;

//Notice, that the class access is default
//Only other package members and access this class
class TruckImpl extends VehicleImpl implements Truck {
    private int payloadPounds;
    TruckImpl(int payloadPounds, int weightPounds, int horsePower) {
        super(weightPounds, horsePower);
        this.payloadPounds = payloadPounds;
    }

    public void setPayloadPounds(int payloadPounds) {
        this.payloadPounds = payloadPounds;
    }

    private int getTruckWeightPounds(){ return this.payloadPounds + super.getWeightPounds(); }
    public double getSpeedMph(double timeSec){
        return getSpeedMph(timeSec, this.getTruckWeightPounds());
    }
}

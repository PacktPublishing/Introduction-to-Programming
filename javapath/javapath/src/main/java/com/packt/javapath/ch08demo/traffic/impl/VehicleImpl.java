package com.packt.javapath.ch08demo.traffic.impl;

import com.packt.javapath.ch08demo.speedmodel.SpeedModel;
import com.packt.javapath.ch08demo.traffic.Vehicle;

//Notice, that the class access is default
//Only other package members and access this class
abstract class VehicleImpl implements Vehicle {
    private int weightPounds, horsePower;
    private SpeedModel speedModel;
    public VehicleImpl(int weightPounds, int horsePower) {
        this.weightPounds = weightPounds;
        this.horsePower = horsePower;
    }

    //Notice, that the method access is protected
    //Only other package members and children access this method
    protected int getWeightPounds(){ return this.weightPounds; }

    //Notice, that the method access is protected
    //Only other package members and children access this method
    protected double getSpeedMph(double timeSec, int weightPounds){
        if(this.speedModel == null){
            throw new RuntimeException("Speed model is required");
        } else {
            return speedModel.getSpeedMph(timeSec, weightPounds, horsePower);
        }
    }

    public void setSpeedModel(SpeedModel speedModel) {
        this.speedModel = speedModel;
    }

}


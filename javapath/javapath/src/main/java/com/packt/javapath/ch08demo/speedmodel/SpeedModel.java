package com.packt.javapath.ch08demo.speedmodel;

import com.packt.javapath.ch08demo.speedmodel.impl.SpeedModelFactory;

import java.time.Month;

public interface SpeedModel {
    double getSpeedMph(double timeSec, int weightPounds, int horsePower);
    static SpeedModel getInstance(Month month, int dayOfMonth, int hour){
        return SpeedModelFactory.speedModel(month, dayOfMonth, hour);
    }
}

package com.packt.javapath.ch08demo.speedmodel.impl;

import com.packt.javapath.ch08demo.speedmodel.SpeedModel;

import java.time.Month;

public class SpeedModelFactory {
    public static SpeedModel speedModel(Month month, int dayOfMonth, int hour){
        double traction; //depends on month, dayOfMonth, hour
        int m = month.ordinal();
        if(m <= 2 || m == 12){ //winter
            if(hour < 8 || hour > 20) { //night
                traction = 0.2;
            } else {
                traction = 0.3;
            }
        } else if(m <= 5 || m >= 8){ //spring or autumn
            traction = 0.4;
        } else {
            traction = 0.5;
        }
        return new SpeedModelImpl(traction);
    }

}

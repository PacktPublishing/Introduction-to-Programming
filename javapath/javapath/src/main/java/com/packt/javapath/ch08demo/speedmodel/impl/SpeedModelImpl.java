package com.packt.javapath.ch08demo.speedmodel.impl;

import com.packt.javapath.ch08demo.speedmodel.SpeedModel;

class SpeedModelImpl implements SpeedModel {
    private double traction;

    SpeedModelImpl(double traction) {
        this.traction = traction;
    }

    public double getSpeedMph(double timeSec, int weightPounds, int horsePower){
        double v = 2.0 * horsePower * 746 * timeSec * 32.174 / weightPounds;
        return Math.round(Math.sqrt(v) * 0.68 * traction);
    }
}

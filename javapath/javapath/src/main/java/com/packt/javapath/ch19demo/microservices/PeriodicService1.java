package com.packt.javapath.ch19demo.microservices;

import io.vertx.rxjava.core.AbstractVerticle;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class PeriodicService1 extends AbstractVerticle {
    public void start() throws Exception {
        LocalTime start = LocalTime.now();
        vertx.setPeriodic(1000, v-> {
            System.out.println("Beep!");
            if(ChronoUnit.SECONDS.between(start, LocalTime.now()) > 3 ){
                vertx.undeploy(deploymentID());
            }
        });
        System.out.println("Vertical PeriodicService1 deployed");
    }

    public void stop() throws Exception {
        System.out.println("Vertical PeriodicService1 is un-deployed");
    }
}

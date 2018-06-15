package com.packt.javapath.ch19demo.reactivesystem;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.eventbus.EventBus;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class PeriodicServiceBusPublish extends AbstractVerticle {
    private EventBus eb;
    private LocalTime start;
    private String address;
    private String[] caller;
    private int delaySec;

    public PeriodicServiceBusPublish(String address, String[] caller, int delaySec) {
        this.address = address;
        this.caller = caller;
        this.delaySec = delaySec;
    }

    public void start() throws Exception {
        System.out.println(this.getClass().getSimpleName() + "(" + address + ", " + delaySec + ") starts...");
        this.eb = vertx.eventBus();
        this.start = LocalTime.now();
        vertx.setPeriodic(delaySec * 1000, v -> {
            int i = (int)ChronoUnit.SECONDS.between(start, LocalTime.now()) - 1;
            System.out.println(this.getClass().getSimpleName()
                    + " to address " + address + ": " + caller[i]);
            eb.publish(address, caller[i]);
            if(i + 1 == caller.length ){
                vertx.undeploy(deploymentID());
            }
        });
    }
}

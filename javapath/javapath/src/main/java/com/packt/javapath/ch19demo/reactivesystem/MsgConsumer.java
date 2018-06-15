package com.packt.javapath.ch19demo.reactivesystem;

import io.vertx.rxjava.core.AbstractVerticle;

public class MsgConsumer extends AbstractVerticle {
    private String address, name;

    public MsgConsumer(String id, String address) {
        this.address = address;
        this.name = this.getClass().getSimpleName() + "(" + id + "," + address + ")";
    }

    public void start() throws Exception {
        System.out.println(name + " starts...");

        vertx.eventBus().consumer(address).toObservable()
            .subscribe(msg -> {
                String reply = name + " got message: " + msg.body();
                System.out.println(reply);
                if ("undeploy".equals(msg.body())) {
                    vertx.undeploy(deploymentID());
                    reply = name + " undeployed.";
                    System.out.println(reply);
                }
                msg.reply(reply);
            }, Throwable::printStackTrace );
        System.out.println(Thread.currentThread().getName()
                + " is waiting on address " + address + "...");
    }
}

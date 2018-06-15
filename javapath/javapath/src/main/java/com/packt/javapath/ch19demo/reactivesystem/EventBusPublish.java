package com.packt.javapath.ch19demo.reactivesystem;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;

public class EventBusPublish extends AbstractVerticle {
    private int port;
    private String address, name;

    public EventBusPublish(int port, String address) {
        this.port = port;
        this.address = address;
        this.name = this.getClass().getSimpleName() + "(port " + port + ", publish to " + address + ")";
    }

    public void start() throws Exception {
        System.out.println(name + " starts...");
        HttpServer server = vertx.createHttpServer();
        server.requestStream().toObservable()
                .subscribe(request -> {
                    String msg = request.getParam("msg");
                    request.response().setStatusCode(200).end();
                    vertx.eventBus().publish(address, msg);
                    if ("undeploy".equals(msg)) {
                        vertx.undeploy(deploymentID());
                        System.out.println(name + " undeployed.");
                    }
                });
        server.rxListen(port).subscribe();
        System.out.println(Thread.currentThread().getName()
                + " is waiting on port " + port + "...");
    }
}

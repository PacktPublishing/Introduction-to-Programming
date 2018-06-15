package com.packt.javapath.ch19demo.reactivesystem;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import org.apache.commons.lang3.StringUtils;

public class EventBusSend extends AbstractVerticle {
    private int port;
    private String address, name;

    public EventBusSend(int port, String address) {
        this.port = port;
        this.address = address;
        this.name = this.getClass().getSimpleName() + "(port " + port + ", send to " + address + ")";
    }

    public void start() throws Exception {
        System.out.println(name + " starts...");
        HttpServer server = vertx.createHttpServer();
        server.requestStream().toObservable()
                .subscribe(request -> {
                    String msg = request.getParam("msg");
                    request.response().setStatusCode(200).end();
                    vertx.eventBus().rxSend(address, msg).subscribe(reply -> {
                                System.out.println(name + " got reply:\n  " + reply.body());
                            },
                            e -> {
                                if (StringUtils.contains(e.toString(), "NO_HANDLERS")) {
                                    vertx.undeploy(deploymentID());
                                    System.out.println(name + " undeployed.");
                                } else {
                                    e.printStackTrace();
                                }
                            });
                });
        server.rxListen(port).subscribe();
        System.out.println(Thread.currentThread().getName()
                + " is waiting on port " + port + "...");
    }
}


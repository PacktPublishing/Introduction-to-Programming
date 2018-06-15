package com.packt.javapath.ch19demo.microservices;

import io.vertx.rxjava.core.RxHelper;

import static io.vertx.rxjava.core.Vertx.vertx;

public class Demo {
    public static void main(String... args) {
        //deployServer();
        //deployPeriodicService();
        deployServerAndClient();
    }

    private static void deployServer() {
        //vertx().getDelegate().deployVerticle(new HttpServer1(8082));
        RxHelper.deployVerticle(vertx(), new HttpServer1(8082));
        RxHelper.deployVerticle(vertx(), new HttpServer1(8083));
    }

    private static void deployPeriodicService() {
        RxHelper.deployVerticle(vertx(), new PeriodicService1());
    }

    private static void deployServerAndClient() {
        RxHelper.deployVerticle(vertx(), new HttpServer2(8082));
        RxHelper.deployVerticle(vertx(), new PeriodicService2(8082));
    }
}

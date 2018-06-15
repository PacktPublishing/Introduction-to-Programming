package com.packt.javapath.ch19demo.reactivesystem;

import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.core.Vertx;

import java.util.concurrent.TimeUnit;

import static io.vertx.rxjava.core.Vertx.vertx;

public class Demo {
    public static void main(String... args) {
        send();
        //publish();
    }

    private static void send() {
        String address = "One";
        Vertx vertx = vertx();
        RxHelper.deployVerticle(vertx, new MsgConsumer("1",address));
        RxHelper.deployVerticle(vertx, new MsgConsumer("2",address));
        RxHelper.deployVerticle(vertx, new EventBusSend(8082, address));
    }

    private static void publish() {
        String address = "One";
        Vertx vertx = vertx();
        RxHelper.deployVerticle(vertx, new MsgConsumer("1",address));
        RxHelper.deployVerticle(vertx, new MsgConsumer("2",address));
        RxHelper.deployVerticle(vertx, new EventBusPublish(8082, address));

    }

    private static void delayMs(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
        }

    }
}




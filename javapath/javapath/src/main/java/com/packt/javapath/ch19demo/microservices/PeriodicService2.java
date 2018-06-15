package com.packt.javapath.ch19demo.microservices;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.buffer.Buffer;
import io.vertx.rxjava.ext.web.client.HttpResponse;
import io.vertx.rxjava.ext.web.client.WebClient;
import rx.Single;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class PeriodicService2 extends AbstractVerticle {
    private int port;

    public PeriodicService2(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        WebClient client = WebClient.create(vertx);
        Single<HttpResponse<Buffer>> single = client
                .get(port, "localhost", "?name=Nick")
                .rxSend();
        LocalTime start = LocalTime.now();
        vertx.setPeriodic(1000, v -> {
            single.subscribe(r -> System.out.println(r.bodyAsString()),
                    Throwable::printStackTrace);

            if (ChronoUnit.SECONDS.between(start, LocalTime.now()) >= 3) {
                client.close();
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                vertx.undeploy(deploymentID());
                System.out.println("Vertical PeriodicService2 undeployed");
            }
        });
        System.out.println("Vertical PeriodicService2 deployed");
    }

    public void start1() throws Exception {
        WebClient client = WebClient.create(vertx);
        Single<HttpResponse<Buffer>> single = client
                .get(port, "localhost", "?name=Nick")
                .rxSend();
        LocalTime start = LocalTime.now();
        vertx.setPeriodic(1000, v -> {
            single.subscribe(r -> System.out.println(r.bodyAsString()),
                    Throwable::printStackTrace);

            if (ChronoUnit.SECONDS.between(start, LocalTime.now()) > 3) {
                vertx.undeploy(deploymentID());
                System.out.println("Vertical PeriodicService2 undeployed");
            }
        });
        System.out.println("Vertical PeriodicService2 deployed");
    }
}


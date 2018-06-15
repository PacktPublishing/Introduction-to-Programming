package com.packt.javapath.ch19demo.microservices;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;

public class HttpServer1 extends AbstractVerticle{
    private int port;
    public HttpServer1(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        server.requestStream().toObservable()
                .subscribe(request -> request.response()
                        .end("Hello from " + Thread.currentThread().getName() + " on port " + port + "!\n\n")
                );
        server.rxListen(port).subscribe();
        System.out.println(Thread.currentThread().getName() + " is waiting on port " + port + "...");
    }
}
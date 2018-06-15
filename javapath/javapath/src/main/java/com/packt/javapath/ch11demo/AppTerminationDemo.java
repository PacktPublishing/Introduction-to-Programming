package com.packt.javapath.ch11demo;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class AppTerminationDemo {

    public static void main(String... args) {

        Thread thr1 = new AThread(1, 4);
        thr1.start();

        Thread thr2 = new AThread(11, 14);
        thr2.setDaemon(true);
        thr2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
            //throw new RuntimeException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread exists");
        //System.exit(0);
        //Runtime.getRuntime().exit(0);
        //Runtime.getRuntime().halt(0);
    }

    private static class AThread extends Thread {
        int i1, i2;

        AThread(int i1, int i2) {
            this.i1 = i1;
            this.i2 = i2;
        }

        public void run() {
            for(int i = i1; i <= i2; i++){
                System.out.println("child thread " + (isDaemon()?"daemon":"user") + " " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

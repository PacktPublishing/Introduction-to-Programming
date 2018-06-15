package com.packt.javapath.ch13demo;

import java.util.EnumSet;

public class EnumSetDemo {
    enum Transport {AIRPLANE, BUS, CAR, TRAIN, TRUCK}

    public static void main(String... args){


        EnumSet<Transport> set1 = EnumSet.allOf(Transport.class);
        System.out.println(set1);      //prints: [AIRPLANE, BUS, CAR, TRAIN, TRUCK]

        EnumSet<Transport> set2 = EnumSet.range(Transport.BUS, Transport.TRAIN);
        System.out.println(set2);      //prints: [BUS, CAR, TRAIN]

        EnumSet<Transport> set3 = EnumSet.of(Transport.BUS, Transport.TRUCK);
        System.out.println(set3);      //prints: [BUS, TRUCK]

        EnumSet<Transport> set4 = EnumSet.complementOf(set3);
        System.out.println(set4);      //prints: [AIRPLANE, CAR, TRAIN]
    }

}

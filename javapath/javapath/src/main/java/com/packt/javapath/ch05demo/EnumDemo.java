package com.packt.javapath.ch05demo;

import com.packt.javapath.ch05demo.theblows.TheBlows;
import com.packt.javapath.ch05demo.thejohns.TheJohns;

public class EnumDemo {
    public static void main(String... args){
        System.out.println();
        System.out.println(TheBlows.BILL.getName());
        System.out.println(TheBlows.BILL.getHobby());
        TheBlows.BILL.setHobby("fishing");
        System.out.println(TheBlows.BILL.getHobby());

        System.out.println();

        System.out.println(TheJohns.JOE.getName());
        System.out.println(TheJohns.JOE.getHobby());
        TheJohns.JOE.setHobby("fishing");
        System.out.println(TheJohns.JOE.getHobby());


        System.out.println(com.packt.javapath.ch05demo.family.TheBlows.BILL.getName());
        System.out.println(com.packt.javapath.ch05demo.family.TheBlows.BILL.getHobby());
        com.packt.javapath.ch05demo.family.TheBlows.BILL.setHobby("fishing");
        System.out.println(com.packt.javapath.ch05demo.family.TheBlows.BILL.getHobby());
    }
}

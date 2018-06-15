package com.packt.javapath.ch08demo.hiding;

public class DemoMain {
    public static void main(String... args){
        System.out.println();
        Grandad grandad = new Child();
        System.out.println(grandad.name);
        System.out.println(grandad.getName());
    }
}

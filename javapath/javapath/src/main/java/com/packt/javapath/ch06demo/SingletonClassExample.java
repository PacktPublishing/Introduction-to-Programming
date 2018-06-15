package com.packt.javapath.ch06demo;

public class SingletonClassExample {
    private static SingletonClassExample OBJECT = null;

    private SingletonClassExample(){}

    public final SingletonClassExample getInstance() {
         if(OBJECT == null){
             OBJECT = new SingletonClassExample();
         }
         return OBJECT;
    }

    //... other class functionality
}

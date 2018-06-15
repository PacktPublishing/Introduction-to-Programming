package com.packt.javapath.ch02demo;

public class MyClass { // top-level class
    public static void main(String... args){
        MyClass myClass = new MyClass();
        myClass.someMethod1();

        myClass = new MyClass() { //Anonymous extends class MyClass
            public void someMethod1(){
                System.out.println("2. Anonymous is called");
            }
        };
        myClass.someMethod1();

        myClass.someMethod2(new InterfaceA() { //Anonymous class implements InterfaceA
            public void doSomething(){
                System.out.println("3. Anonymous is called");
            }
        });

    }

    void someMethod1() {
        System.out.println("1. Regular is called");
    }

    void someMethod2(InterfaceA interfaceA) {
        interfaceA.doSomething();
    }

    interface InterfaceA{
        void doSomething();
    }


}
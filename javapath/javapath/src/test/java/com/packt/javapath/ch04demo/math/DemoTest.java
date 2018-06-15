package com.packt.javapath.ch04demo.math;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class DemoTest {

    @BeforeAll
    static void beforeAll(){
        System.out.println("beforeAll is executed");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("afterAll is executed");
    }


    @Test
    void test1(){
        System.out.println("test1 is executed");
    }

    @Test
    void test2(){
        System.out.println("test2 is executed");
    }

}

package com.packt.javapath.ch06demo.api.impl;

import com.packt.javapath.ch06demo.api.Calculator;

class AnotherCalculatorImpl  implements Calculator {

    public static String addOneAndConvertToString(double d){
        System.out.println(AnotherCalculatorImpl.class.getName());
        return String.format("%.2f", d + 1);
    }

    public int multiplyByTwo(int i){
        System.out.println(AnotherCalculatorImpl.class.getName());
        return i + i;
    }
}

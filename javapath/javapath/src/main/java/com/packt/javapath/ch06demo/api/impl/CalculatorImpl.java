package com.packt.javapath.ch06demo.api.impl;

import com.packt.javapath.ch06demo.api.Calculator;

class CalculatorImpl implements Calculator {

    public static String addOneAndConvertToString(double d){
        System.out.println(CalculatorImpl.class.getName());
        return Double.toString(d + 1);
    }

    public int multiplyByTwo(int i){
        System.out.println(CalculatorImpl.class.getName());
        return i * 2;
    }
}

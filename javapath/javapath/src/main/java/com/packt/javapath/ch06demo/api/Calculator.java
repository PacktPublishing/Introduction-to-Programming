package com.packt.javapath.ch06demo.api;

import com.packt.javapath.ch06demo.api.impl.CalculatorFactory;

public interface Calculator {

    int multiplyByTwo(int i);

    static Calculator createInstance(){
        return  CalculatorFactory.create();
    }

    static String addOneAndConvertToString(double d){
        return  CalculatorFactory.addOneAndConvertToString(d);
    }

    String CONF_NAME = "calculator.conf";
    String CONF_WHICH_IMPL = "which.impl";
    enum WhichImpl{
        multiplies, //use multiplication operation
        adds        //use addition operation
    }

}

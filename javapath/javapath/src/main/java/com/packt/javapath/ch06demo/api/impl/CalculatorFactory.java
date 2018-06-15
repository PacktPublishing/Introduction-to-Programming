package com.packt.javapath.ch06demo.api.impl;

import com.packt.javapath.ch06demo.Utils;
import com.packt.javapath.ch06demo.api.Calculator;
import com.packt.javapath.ch06demo.api.Calculator.WhichImpl;

public class CalculatorFactory {

    public static String addOneAndConvertToString(double d){
        WhichImpl whichImpl =
                Utils.getWhichImplValueFromConfig(Calculator.CONF_NAME,
                                                  Calculator.CONF_WHICH_IMPL);
        switch (whichImpl){
            case multiplies:
                return CalculatorImpl.addOneAndConvertToString(d);
            case adds:
                return AnotherCalculatorImpl.addOneAndConvertToString(d);
            default:
                throw new RuntimeException("Houston, we have another problem. " +
                        "We do not have implementation for the key " +
                        Calculator.CONF_WHICH_IMPL + " value " + whichImpl);
        }
    }

    public static Calculator create(){
        WhichImpl whichImpl = Utils.getWhichImplValueFromConfig(Calculator.CONF_NAME, Calculator.CONF_WHICH_IMPL);
        switch (whichImpl){
            case multiplies:
                return new CalculatorImpl();
            case adds:
                return new AnotherCalculatorImpl();
            default:
                throw new RuntimeException("Houston, we have another problem. " +
                        "We do not have implementation for the key " +
                        Calculator.CONF_WHICH_IMPL + " value " + whichImpl);
        }
    }

    public static Calculator create2(){
        String whichImpl = Utils.getStringValueFromConfig(Calculator.CONF_NAME, Calculator.CONF_WHICH_IMPL);
        if(whichImpl.equals(Calculator.WhichImpl.multiplies.name())){
            return new CalculatorImpl();
        } else if (whichImpl.equals(Calculator.WhichImpl.adds.name())){
            return new AnotherCalculatorImpl();
        } else {
            throw new RuntimeException("Houston, we have a problem. " +
                    "Unknown key " + Calculator.CONF_WHICH_IMPL +
                    " value " + whichImpl + " is in config.");
        }
    }


    public static Calculator create1(){
        String whichImpl = Utils.getStringValueFromConfig("calculator.conf", "which.impl");
        if(whichImpl.equals("multiplies")){
            return new CalculatorImpl();
        } else if (whichImpl.equals("adds")){
            return new AnotherCalculatorImpl();
        } else {
            throw new RuntimeException("Houston, we have a problem. " +
                    "Unknown key which.impl value " + whichImpl +
                    " is in config.");
        }

    }

    public static Calculator create4(){
        String whichImpl = Utils.getStringValueFromConfig("calculator.conf", "which.impl");
        if(whichImpl.equals("multiplies")){
            return new Whatever();
        } else if (whichImpl.equals("adds")){
            return new AnotherCalculatorImpl();
        } else {
            throw new RuntimeException("Houston, we have a problem. " +
                    "Unknown key which.impl value " + whichImpl +
                    " is in config.");
        }

    }

    static class Whatever implements Calculator {

        public static String addOneAndConvertToString(double d){
            System.out.println(Whatever.class.getName());
            return Double.toString(d + 1);
        }

        public int multiplyByTwo(int i){
            System.out.println(Whatever.class.getName());
            return i * 2;
        }
    }

}

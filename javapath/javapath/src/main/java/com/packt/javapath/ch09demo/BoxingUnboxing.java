package com.packt.javapath.ch09demo;

public class BoxingUnboxing {
    public static void main(String[] args) {

        int n = 12;
        Integer integer = n; //an example of autoboxing
        System.out.println(integer);               //prints: 12
        integer = Integer.valueOf(n);
        System.out.println(integer);               //prints: 12

        Byte b = Byte.valueOf((byte)n);
        Short s = Short.valueOf((short)n);
        Long l = Long.valueOf(n);
        Float f = Float.valueOf(n);
        Double d = Double.valueOf(n);

        System.out.println(integer);               //prints: 12
        System.out.println(integer.intValue());    //prints: 12
        System.out.println(integer.byteValue());   //prints: 12
        System.out.println(integer.shortValue());  //prints: 12
        System.out.println(integer.longValue());   //prints: 12
        System.out.println(integer.floatValue());  //prints: 12.0
        System.out.println(integer.doubleValue()); //prints: 12.0

        Long longWrapper = Long.valueOf(12L);
        long lng = longWrapper;    //implicit unboxing
        System.out.println(lng);   //prints: 12
    }
}
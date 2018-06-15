package com.packt.javapath.ch07demo;

public class Ch07DemoApp {

    private String x = "x";

    public static void main(String[] args){
        String s1 = "1";
        String s2 = "2";
        System.out.println(sum(s1, s2));
        System.out.println(sum1(s1, s2));
        s1 = "1.1";
        System.out.println(sum2(s1, s2));
        System.out.println(sum3(s1, s2));
        s2 = "2.1";
        System.out.println(sum4(s1, s2));

    }

    public static long sum(String s1, String s2){
        int i1 = Integer.parseInt(s1);
        int i2 = Integer.parseInt(s2);
        return i1 + i2;
    }

    public static long sum1(String s1, String s2){
        long l1 = Long.parseLong(s1);
        long l2 = Long.parseLong(s2);
        return l1 + l2;
    }

    public static long sum2(String s1, String s2){
        long l1 = 0;
        try{
            l1 = Long.parseLong(s1);
        } catch (NumberFormatException ex){
            //make a record to a log
        }
        long l2 = 0;
        try{
            l2 = Long.parseLong(s2);
        } catch (NumberFormatException ex){
            //make a record to a log
        }
        return l1 + l2;
    }

    private static long getLong(String s){
        long l = 0;
        try{
            l = Long.parseLong(s);
        } catch (NumberFormatException ex){
            //make a record to a log
        }
        return l;
    }

    public static long sum3(String s1, String s2){
        return getLong(s1) + getLong(s2);
    }

    private static long getLong2(String s){
        double d = 0;
        try{
            d = Double.parseDouble(s);
        } catch (NumberFormatException ex){
            //make a record to a log
        }
        return Math.round(d);
    }

    public static long sum4(String s1, String s2){
        return getLong2(s1) + getLong2(s2);
    }

    public static long sum(int i, String s2){
        return i + getLong2(s2);
    }

    public void printX(){
        System.out.println(x);
        String x = "y";
        System.out.println(x);
    }
}

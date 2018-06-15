package com.packt.javapath.ch09demo;

public class WideningNarrowing {
    public static void main(String[] args) {

        int n = 1234567899;
        float f = (float)n;
        int r = n - (int)f;
        System.out.println(r);    //prints: -46

        double d = (double)n;
        r = n - (int)d;
        System.out.println(r);    //prints: 0


        long l = 1234567899123456L;
        f = (float)l;
        long rl = l - (long)f;
        System.out.println(rl);    //prints: -49017088

        d = (double)l;
        rl = l - (long)d;
        System.out.println(rl);    //prints: 0

        l = 12345678991234567L;
        d = (double)l;
        rl = l - (long)d;
        System.out.println(rl);    //prints: -1

        double dd = 1234567890.0;
        System.out.println(Integer.MAX_VALUE); //prints: 2147483647
        if(dd < Integer.MAX_VALUE){
            int nn = (int)dd;
            System.out.println(nn);            //prints: 1234567890
        } else {
            System.out.println(dd - Integer.MAX_VALUE);
        }

        dd = 2234567890.0;
        System.out.println(Integer.MAX_VALUE); //prints: 2147483647
        if(dd < Integer.MAX_VALUE){
            int nn = (int)dd;
            System.out.println(nn);
        } else {
            System.out.println(dd - Integer.MAX_VALUE); //prints: 8.7084243E7
        }
    }

}

package com.packt.javapath.ch07demo.pack02;

import com.packt.javapath.ch07demo.pack01.PublicClass01;
import com.packt.javapath.ch07demo.pack01.PublicInterface01;

//import com.packt.javapath.ch07demo.pack01.DefaultAccessClass01;
//import com.packt.javapath.ch07demo.pack01.DefaultAccessInterface01;

public class PublicClass02 {
    public static void main(String[] args){

        System.out.println(PublicInterface01.name);
        PublicClass01 o = new PublicClass01();

    }

    private String field;
    private static int count;
    private PublicClass02(String s){
        this.field = s;
    }
    public static PublicClass02 getInstance(String s){
        if(count > 5){
            return null;
        } else {
            count++;
            return new PublicClass02(s);
        }
    }

}

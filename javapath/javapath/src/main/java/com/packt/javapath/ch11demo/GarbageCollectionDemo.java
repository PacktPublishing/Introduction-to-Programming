package com.packt.javapath.ch11demo;

import java.util.ArrayList;
import java.util.List;

public class GarbageCollectionDemo {
    public static void main(String... args) {
        int max = 99888999;
        //System.out.println("GarbageCollectionDemo.main() is running for " + max + " objects...");
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < max; i++){
            list.add(Integer.valueOf(i));
        }
    }
}

package com.packt.javapath.ch15demo;

import java.util.Random;
import java.util.stream.Collectors;

public class ManageRandomNumber {
    public static void main(String... args){
        classRandom();
        mathRandom();
    }

    private static void classRandom(){
        Random random = new Random();
        for(int i =0; i < 3; i++){
            System.out.print(random.nextDouble() + " "); //0.8774928230544553 0.7822070124559267 0.09401796000707807
        }
        System.out.println();
        for(int i =0; i < 3; i++){
            System.out.print(random.nextInt() + " "); //-2001537190 -1148252160 1999653777
        }
        System.out.println();
        for(int i =0; i < 3; i++){
            System.out.print(random.nextInt(11) + " "); //prints: 4 6 2
        }
        System.out.println();
        for(int i =0; i < 3; i++){
            System.out.print(11 + random.nextInt(10) + " "); //prints: 13 14 20
        }
        System.out.println();
        String result = random.ints(3,0, 101)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")); //prints: 30 48 52
        System.out.println(result);

    }

    private static void mathRandom(){
        for(int i =0; i < 3; i++){
            System.out.println(Math.random());
            //0.9350483840148613
            //0.0477353019234189
            //0.25784245516898985
        }

        for(int i =0; i < 3; i++){
            System.out.print(getInteger(10) + " "); //prints: 2 5 6
        }
        System.out.println();
        for(int i =0; i < 3; i++){
            System.out.print(getInteger(100) + " "); //prints: 48 11 97
        }
        System.out.println();
        for(int i =0; i < 3; i++){
            System.out.print(100 + getInteger(100) + " "); //prints: 114 101 127
        }
        System.out.println();
        for(int i =0; i < 3; i++){
            System.out.print(100 + getIntegerRound(100) + " "); //prints: 179 147 200
        }
        System.out.println();
        for(int i =0; i < 3; i++){
            System.out.print(100 + getInteger2(100) + " "); //prints: 167 200 132
        }
    }

    private static int getInteger(int max){
        return (int)(Math.random() * max);
    }

    private static int getIntegerRound(int max){
        return (int)Math.round(Math.random() * max);
    }

    private static int getInteger2(int max){
        return (int)(Math.random() * (max + 1));
    }
}

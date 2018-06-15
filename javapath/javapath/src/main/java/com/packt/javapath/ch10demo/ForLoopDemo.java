package com.packt.javapath.ch10demo;

import java.util.List;

public class ForLoopDemo {
    public static void main(String... args) {
        basicForLoop();
        enhancedForLoop();
        forLoopWithMultipleExpressions();
    }

    public static void forLoopWithMultipleExpressions(){
        for (int i=0, j=0; i < 3 && j < 3; ++i, ++j){
            System.out.println(i + " " + j);
        }
        System.out.println();
        for (int x=new A().getInitialValue(), i=x == -2 ? x + 2 : 0, j=0; i < 3 || j < 3; ++i, j = i){
            System.out.println(i + " " + j);
        }
    }

    private static class A{
        int getInitialValue(){
            return -2;
        }
    }
    public static void enhancedForLoop(){
        int[] arr = {21, 34, 5};
        for (int a: arr){
            System.out.print(a + " ");  //prints: 21 34 5
        }
        System.out.println();
        List<String> list = List.of("Bob", "Joe", "Jill");
        for (String s: list){
            System.out.print(s + " ");  //prints: Bob Joe Jill
        }
    }

    public static void basicForLoop(){
        for (int i=0; i < 3; i++){
            System.out.print(i + " ");  //prints: 0 1 2
        }
/*
        for (;;){
            System.out.println("try and stop me"); //prints indefinitely
        }
*/
        System.out.println();
        int k = 0;
        for (;;){
            System.out.print(k++ + " "); //prints: 0 1 2
            if(k > 2) break;
        }
        System.out.println();
        for (int i=0; i < 3;){
            System.out.print(i++ + " "); //prints: 0 1 2
        }
        System.out.println();
/*
        for (int i=2; i > 0; i--){
            System.out.print(i++ + " "); //prints indefinitely
        }
*/
        System.out.println();
        for (int i=2; i > 0; i--){
            System.out.print(i + " "); //prints: 2 1
        }
    }

}

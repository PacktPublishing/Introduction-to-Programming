package com.packt.javapath.ch15demo;

import java.util.Arrays;
import java.util.List;

public class ManageStrings {
    public static void main(String... args){
        stringBuilder();
        format();
        replace();
        index();
        split();
        join();
        startsEndsWith();
    }

    private static void startsEndsWith() {
        boolean b = "Introduction".startsWith("Intro");
        System.out.println(b);             //prints: true

        b = "Introduction".startsWith("tro", 2);
        System.out.println(b);             //prints: true

        b = "Introduction".endsWith("ion");
        System.out.println(b);             //prints: true
    }

    private static void join() {
        String s1 =  "There is a bear";
        String s2 =  " in the woods";
        String s = s1.concat(s2);
        System.out.println(s);  //prints: There is a bear in the woods

        s =  s1 + s2;
        System.out.println(s);  //prints: There is a bear in the woods

        s = String.join(" ", "There", "is", "a", "bear", "in", "the", "woods");
        System.out.println(s);  //prints: There is a bear in the woods

        List<String> list = List.of("There", "is", "a", "bear", "in", "the", "woods");
        s = String.join(" ", list);
        System.out.println(s);  //prints: There is a bear in the woods
    }

    private static void split() {
        String[] substrings = "Introduction".split("o");
        System.out.println(Arrays.toString(substrings)); //prints: [Intr, ducti, n]

        substrings = "Introduction".split("duct");
        System.out.println(Arrays.toString(substrings)); //prints: [Intro, ion]


        String s = "There is a bear in the woods";
        String[] arr = s.split(" ");
        System.out.println(Arrays.toString(arr));  //prints: [There, is, a, bear, in, the, woods]

        arr = s.split(" ", 3);
        System.out.println(Arrays.toString(arr));  //prints: [There, is, a bear in the woods]
    }

    private static void index(){
        String s = "Introduction";
        System.out.println(s.indexOf("I"));          //prints: 0
        System.out.println(s.lastIndexOf("I"));  //prints: 0
        System.out.println(s.lastIndexOf("i"));  //prints: 9
        System.out.println(s.indexOf("o"));          //prints: 4
        System.out.println(s.lastIndexOf("o"));  //prints: 10
        System.out.println(s.indexOf("tro"));        //prints: 2

        //String s = "Introduction";
        System.out.println(s.substring(1));        //prints: ntroduction
        System.out.println(s.substring(2));        //prints: troduction

        //String s = "Introduction";
        System.out.println(s.substring(1, 2));        //prints: n
        System.out.println(s.substring(1, 3));        //prints: nt

        System.out.println(s.substring(1));              //prints: ntroduction
        System.out.println(s.substring(1, s.length()));  //prints: ntroduction

        //String s = "Introduction";
        System.out.println(s.contains("x"));          //prints: false
        System.out.println(s.contains("o"));          //prints: tru
        System.out.println(s.contains("tro"));        //prints: true
        System.out.println(s.contains("trx"));        //prints: false

    }

    private static void replace(){
        String s1 = "There is a bear in the woods";
        String s2 = s1.replace("bear", "horse").replace("woods", "field");
        System.out.println(s2); //prints: There is a horse in the field
    }

    private static void format(){
        String format = "There is a %s in the %s";
        String s = String.format(format, "bear", "woods");
        System.out.println(s); //prints: There is a bear in the woods

        format = "Class %s is very useful";
        s = String.format(format, new A());
        System.out.println(s);  //prints: Class A is very useful
    }

    static class A {
        @Override
        public String toString(){
            return "A";
        }
    }

    private static void stringBuilder(){
        List<String> list = List.of("That", "is", "the", "way", "to", "build", "a", "sentence");
        StringBuilder sb = new StringBuilder();
        for(String s: list){
            sb.append(s).append(" ");
        }
        String s = sb.toString();
        System.out.println(s);  //prints: That is the way to build a sentence
    }


}

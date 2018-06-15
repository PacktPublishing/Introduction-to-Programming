package com.packt.javapath.ch13demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class SetDemo {
    public static void main(String... args) {
        add();
        iterate();
        removeIf();
        replace();
        diff();
        common();
        equals();
        toArray();
    }

    private static void toArray(){
        Set<String> set = new HashSet<>();
        set.add("s1");
        set.add("s2");

        Object[] arr1 = set.toArray();
        for(Object o: arr1){
            System.out.print(o);       //prints: s1s2
        }

        System.out.println();

        String[] arr2 = set.toArray(new String[set.size()]);

        for(String s: arr2){
            System.out.print(s);     //prints: s1s2
        }

        System.out.println();

        Object[] arr3 = set.stream().toArray();
        for (Object o : arr3) {
            System.out.print(o);       //prints: s1s2
        }

        System.out.println();

        String[] arr4 = set.stream().toArray(String[]::new);
        for (String s : arr4) {
            System.out.print(s);       //prints: s1s2
        }
    }

    private static void equals(){
        Set<String> set1 = new HashSet<>();
        set1.add("s1");
        set1.add("s2");

        List<String> list = new ArrayList<>();
        list.add("s2");
        list.add("s1");

        System.out.println(set1.equals(list));  //prints: false

        Set<String> set2 = new HashSet<>();
        set2.add("s3");
        set2.add("s1");

        System.out.println(set1.equals(set2));  //prints: false

        set2.remove("s3");
        set2.add("s2");
        System.out.println(set1.equals(set2));  //prints: true
    }

    private static void common(){
        Set<String> set1 = new HashSet<>();
        set1.add("s1");
        set1.add("s1");
        set1.add("s2");
        set1.add("s3");
        set1.add("s4");

        Set<String> set2 = new HashSet<>();
        set2.add("s1");
        set2.add("s2");
        set2.add("s2");
        set2.add("s5");

        Set<String> set = new HashSet<>(set1);
        set.retainAll(set2);
        System.out.println(set);    //prints: [s1, s2]

        set = new HashSet<>(set2);
        set.retainAll(set1);
        System.out.println(set);    //prints: [s1, s2]

    }
    private static void diff(){

        Set<String> set1 = new HashSet<>();
        set1.add("s1");
        set1.add("s1");
        set1.add("s2");
        set1.add("s3");
        set1.add("s4");

        Set<String> set2 = new HashSet<>();
        set2.add("s1");
        set2.add("s2");
        set2.add("s2");
        set2.add("s5");

        Set<String> set = new HashSet<>(set1);
        set.removeAll(set2);
        System.out.println(set);    //prints: [s3, s4]

        set = new HashSet<>(set2);
        set.removeAll(set1);
        System.out.println(set);    //prints: [s5]

    }
    private static void replace(){
        System.out.println();

        Set<String> set = new HashSet();
        set.add(null);
        set.add("s1");
        set.add("s1");
        set.add("s2");
        set.add("s3");
        set.add("s4");
        System.out.println(set);    //[null, s3, s4, s1, s2]

        //We want to replace s2 with s5
        Set<String> newSet = new HashSet<>();
        set.forEach(s -> {
            if("s2".equals(s)){
                newSet.add("s5");
            } else {
                newSet.add(s);
            }
        });
        set = newSet;
        System.out.println(set);    //[null, s3, s4, s5, s1]
    }


    private static void removeIf(){
        System.out.println();

        Set<String> set = new HashSet();
        set.add(null);
        set.add("s1");
        set.add("s1");
        set.add("s2");
        set.add("s3");
        set.add("s4");
        System.out.println(set);    //[null, s3, s4, s1, s2]

        set.removeIf(e -> "s1".equals(e));
        System.out.println(set);   //[null, s3, s4, s2]

        set.removeIf(e -> e == null);
        System.out.println(set);    //[s3, s4, s2]

    }

    private static boolean filter(Predicate<? super B> p, C e){
        return p.test(e);
    }


    private static void iterate(){
        System.out.println();

        Set set = new HashSet();
        set.add(null);
        set.add(1);
        set.add("ss");
        set.add(new A());
        set.add(new B());

        for(Object o: set){
            System.out.println(o);
        }

        set.forEach(System.out::println);

    }

    private static void add(){

        Set<String> set = new HashSet<>();
        System.out.println(set.add("s1"));  //prints: true
        System.out.println(set.add("s1"));  //prints: false
        System.out.println(set.add("s2"));  //prints: true
        System.out.println(set.add("s3"));  //prints: true
        System.out.println(set);            //prints: [s3, s1, s2]  //changes order for every run?

        Set<String> set1 = new HashSet<>();
        set1.add("s1");
        set1.add("s2");
        set1.add("s3");

        List<String> list = new ArrayList<>();
        list.add("s1");

        System.out.println(set1.addAll(list)); //prints: false
        System.out.println(set1);              //prints: [s3, s1, s2]

        list.add("s4");
        System.out.println(set1.addAll(list)); //prints: true
        System.out.println(set1);              //prints: [s3, s4, s1, s2]
    }

    private static void methodGenerics(Set<B> set){
        //set.add(new Object()); //compilation error
        //set.add(new A());      //compilation error
        set.add(new B());
        set.add(new C());
        //set.add(new D());      //compilation error

        for(Object e: set){
            System.out.println(e);
        }
        for(A e: set){
            System.out.println(e);
        }
        for(B e: set){
            System.out.println(e);
        }
        for(I e: set){
            System.out.println(e);
        }
/*
        for(C e: set){     //compilation error
            System.out.println(e);
        }
        for(D e: set){    //compilation error
            System.out.println(e);
        }
*/
    }

    private static void methodGenerics1(Set<? super B> set){
        //set.add(new Object()); //compilation error
        //set.add(new A());      //compilation error
        set.add(new B());
        set.add(new C());
        //set.add(new D());      //compilation error

        for(Object e: set){
            System.out.println(e);
        }
/*
        for(A e: set){     //compilation error
            System.out.println(e);
        }
        for(B e: set){     //compilation error
            System.out.println(e);
        }
        for(I e: set){    //compilation error
            System.out.println(e);
        }
        for(C e: set){     //compilation error
            System.out.println(e);
        }
        for(D e: set){    //compilation error
            System.out.println(e);
        }
*/

    }

    static void methodGenerics2(Set<? super I> set){
        //set.add(new Object()); //compilation error
        //set.add(new A());      //compilation error
        set.add(new B());
        set.add(new C());
        set.add(new D());

        for(Object e: set){
            System.out.println(e);
        }
/*
        for(A e: set){     //compilation error
            System.out.println(e);
        }
        for(B e: set){     //compilation error
            System.out.println(e);
        }
        for(I e: set){    //compilation error
            System.out.println(e);
        }
        for(C e: set){     //compilation error
            System.out.println(e);
        }
        for(D e: set){    //compilation error
            System.out.println(e);
        }
*/

    }

    static void methodGenerics3(Set<? extends B> set){
        //set.add(new Object()); //compilation error
        //set.add(new A());      //compilation error
        //set.add(new B());      //compilation error
        //set.add(new C());      //compilation error
        //set.add(new D());      //compilation error

        for(Object e: set){
            System.out.println(e);
        }
        for(A e: set){
            System.out.println(e);
        }
        for(B e: set){
            System.out.println(e);
        }
        for(I e: set){
            System.out.println(e);
        }
/*
        for(C e: set){     //compilation error
            System.out.println(e);
        }
        for(D e: set){    //compilation error
            System.out.println(e);
        }
*/

    }

    static void methodGenerics4(Set<? extends I> set){
        //set.add(new Object()); //compilation error
        //set.add(new A());      //compilation error
        //set.add(new B());      //compilation error
        //set.add(new C());      //compilation error
        //set.add(new D());      //compilation error

        for(Object e: set){
            System.out.println(e);
        }
/*
        for(A e: set){    //compilation error
            System.out.println(e);
        }
        for(B e: set){    //compilation error
            System.out.println(e);
        }
*/
        for(I e: set){
            System.out.println(e);
        }
/*
        for(C e: set){     //compilation error
            System.out.println(e);
        }
        for(D e: set){    //compilation error
            System.out.println(e);
        }
        for(D e: set){    //compilation error
            System.out.println(e);
        }
*/
    }

    interface I {
        void method();
    }
    static class A {
        @Override
        public String toString() { return "A"; }
    }
    static class B extends A implements I {
        public void method(){}
        @Override
        public String toString() { return "B"; }
    }
    static class C extends B {
        @Override
        public String toString() { return "C"; }
    }
    static class D implements I {
        public void method(){}
        @Override
        public String toString() { return "B"; }
    }

}

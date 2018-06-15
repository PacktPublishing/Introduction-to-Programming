package com.packt.javapath.ch17demo;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Functionalnterface {
    public static void main(String... args) {

        function1();
        function2();
        consumer1();
        consumer2();
        processAndConsume();
        supplyProcessAndConsume();
        supplyDecideProcessAndConsume();
        chain();
        identity();
    }

    private static void identity(){
        Function<Integer, Integer> id = Function.identity();
        System.out.println(id.apply(4));          //prints: 4

        Function<Double, Double> multiplyByFive = Function.identity();
        System.out.println(multiplyByFive.apply(2.));  //prints: 2.0

        Function<Double, Long> subtract7 = createSubtractInt(7);
        System.out.println(subtract7.apply(11.0));   //prints: 4

        long r = multiplyByFive.andThen(subtract7).apply(2.);
        System.out.println(r);                          //prints: -5
    }


    private static void chain(){

        Function<Double, Long> f1 = d -> Double.valueOf(d / 2.).longValue();
        Function<Long, String> f2 = l -> "Result: " + (l + 1);
        Function<Double, String> f3 = f1.andThen(f2);
        System.out.println(f3.apply(4.));

        Function<Integer, Double> multiplyByFive = createMultiplyBy(5);
        System.out.println(multiplyByFive.apply(2));  //prints: 10.0

        Function<Double, Long> subtract7 = createSubtractInt(7);
        System.out.println(subtract7.apply(11.0));   //prints: 4

        long r = multiplyByFive.andThen(subtract7).apply(2);
        System.out.println(r);                          //prints: 3

        r = subtract7.compose(multiplyByFive).apply(2);
        System.out.println(r);                          //prints: 3

        Consumer<Double> print21By = createPrintingFunc("21 by ", "");
        Consumer<Double> equalsBy21 = createPrintingFunc("equals ", " by 21");
        print21By.andThen(equalsBy21).accept(2d);  //prints: 21 by 2.0 equals 2.0 by 21


        Predicate<Double> isSmallerThan20 = testSmallerThan(20d);
        System.out.println(isSmallerThan20.test(10d));
             //prints: Test if 10.0 is smaller than 20.0
             //        true

        Predicate<Double> isBiggerThan18 = testBiggerThan(18d);
        System.out.println(isBiggerThan18.test(10d));
            //prints: Test if 10.0 is bigger than 18.0
            //        false

        boolean b = isSmallerThan20.and(isBiggerThan18).test(10.);
        System.out.println(b);
            //prints: Test if 10.0 is smaller than 20.0
            //        Test if 10.0 is bigger than 18.0
            //        false

        b = isSmallerThan20.or(isBiggerThan18).test(10.);
        System.out.println(b);
            //prints: Test if 10.0 is smaller than 20.0
            //        true
    }

    private static Predicate<Double> testSmallerThan(double limit){
        Predicate<Double> func = new Predicate<Double>() {
            public boolean test(Double num) {
                System.out.println("Test if " + num + " is smaller than " + limit);
                return num < limit;
            }
        };
        return func;
    }
    private static Predicate<Double> testBiggerThan(double limit){
        Predicate<Double> func = new Predicate<Double>() {
            public boolean test(Double num) {
                System.out.println("Test if " + num + " is bigger than " + limit);
                return num > limit;
            }
        };
        return func;
    }
    private static Function<Double, Long> createSubtractInt(int num){
        Function<Double, Long> func = new Function<Double, Long>(){
            public Long apply(Double dbl){
                return Math.round(dbl - num);
            }
        };
        return func;
    }

    private static void supplyDecideProcessAndConsume(){
        Supplier<Integer> input = createSuppplier(3);
        Predicate<Integer> test = createTestSmallerThan(5);
        Function<Integer, Double> multiplyByFive = createMultiplyBy(5);
        Consumer<Double> printResult = createPrintingFunc("Result=", " Great!");
        supplyDecideProcessAndConsume(input, test, multiplyByFive, printResult);

    }

    private static void supplyDecideProcessAndConsume(Supplier<Integer> input, Predicate<Integer> test, Function<Integer, Double> process, Consumer<Double> consume){
        int in = input.get();
        if(test.test(in)){
            consume.accept(process.apply(input.get()));
        } else {
            System.out.println("Input " + in + " does not pass the test and not processed.");
        }
    }

    private static Predicate<Integer> createTestSmallerThan(int num){
        Predicate<Integer> func = new Predicate<Integer>() {
            public boolean test(Integer d) {
                return d < num;
            }
        };
        return func;
    }

    private static void supplyProcessAndConsume(){
        Supplier<Integer> supply7 = createSuppplier(7);
        Function<Integer, Double> multiplyByFive = createMultiplyBy(5);
        Consumer<Double> printResult = createPrintingFunc("Result=", " Great!");
        supplyProcessAndConsume(supply7, multiplyByFive, printResult); //prints: Result=35.0 Great!

    }

    private static void supplyProcessAndConsume(Supplier<Integer> input, Function<Integer, Double> process, Consumer<Double> consume){
        consume.accept(process.apply(input.get()));
    }

    private static Supplier<Integer> createSuppplier(int num){
        Supplier<Integer> func = new Supplier<Integer>() {
            public Integer get() { return num; }
        };
        return func;
    }

    private static void processAndConsume(){
        Function<Integer, Double> multiplyByFive = createMultiplyBy(5);
        Consumer<Double> printResult = createPrintingFunc("Result=", " Great!");
        processAndConsume(10, multiplyByFive, printResult); //prints: Result=50.0 Great!
    }

    private static void processAndConsume(int input, Function<Integer, Double> processingFunc, Consumer<Double> consumer){
        consumer.accept(processingFunc.apply(input));
    }

    private static void consumer2(){
        Consumer<Double> printResult = createPrintingFunc("Result=", " Great!");
        printResult.accept(10.0);         //prints: Result=10.0 Great!
    }

    private static Consumer<Double> createPrintingFunc(String prefix, String postfix){
        Consumer<Double> func = new Consumer<Double>() {
            public void accept(Double d) {
                System.out.println(prefix + d + postfix);
            }
        };
        return func;
    }

    private static void consumer1(){
        Consumer<Double> printResult = new Consumer<Double>() {
            public void accept(Double d) {
                System.out.println("Result=" + d);
            }
        };
        printResult.accept(10.0);         //prints: Result=10.0
    }

    private static void function2(){
        Function<Integer, Double> multiplyByFive = createMultiplyBy(5);
        System.out.println(multiplyByFive.apply(1)); //prints: 5.0
        useFunc(multiplyByFive, 10);              //prints: 50.0
    }

    private static Function<Integer, Double> createMultiplyBy(double num){
        Function<Integer, Double> ourFunc = new Function<Integer, Double>(){
            public Double apply(Integer i){
                return i * num;
            }
        };
        return ourFunc;
    }

    private static void useFunc(Function<Integer, Double> procesingFunc, int input){
        System.out.println(procesingFunc.apply(input));
    }

    private static void function1(){
        Function<Integer, Double> multiplyByTen = new Function<Integer, Double>(){
            public Double apply(Integer i){
                return i * 10.0;
            }
        };
        System.out.println(multiplyByTen.apply(1)); //prints: 10.0

        useFunc(multiplyByTen, 10);              //prints: 100.00

    }

    private static Function<Long, Double> createAddSqrRoot(double num){
        Function<Long, Double> func = new Function<Long, Double>(){
            public Double apply(Long lng){
                return Math.sqrt(num) + lng;
            }
        };
        return func;
    }

    private static Consumer<String> createTalker(String value){
        Consumer<String> consumer = new Consumer<String>() {
            public void accept(String s) {
                System.out.println(s + value);
            }
        };
        return consumer;
    }

    private static Supplier<String> createResultSupplier(){
        Supplier<String> supplier = new Supplier<String>() {
            public String get() {
                String res = "Success";
                //Do something and return result – Success or Error.
                return res;
            }
        };
        return supplier;
    }

    private static Supplier<String> applyCompareAndSay(int i, Function<Integer, Double> func, Predicate<Double> isSmaller){
        Supplier<String> supplier = new Supplier<String>() {
            public String get() {
                double v = func.apply(i);
                return isSmaller.test(v)? v + " is smaller" : v + " is bigger";
            }
        };
        return supplier;
    }

    private static Supplier<String> applyCompareAndSay2(int i, Function<Integer, Long> func, Predicate<Double> isSmaller){
        Supplier<String> supplier = new Supplier<String>() {
            public String get() {
                double v = func.apply(i);
                return isSmaller.test(v)? v + " is smaller" : v + " is bigger";
            }
        };
        return supplier;
    }

    private static Supplier<String> applyCompareAndSay(int i, Function<Integer, Double> func, Predicate<Double> compare, String message){
        Supplier<String> supplier = new Supplier<String>() {
            public String get() {
                double v = func.apply(i);
                return (compare.test(v)? v + " is " : v + " is not ") + message;
            }
        };
        return supplier;
    }

    private static Supplier<String> applyCompareAndSayLambda(int i, Function<Integer, Double> func, Predicate<Double> isSmaller){
        return () -> {
            double v = func.apply(i);
            return isSmaller.test(v)? v + " is smaller" : v + " is bigger";
        };
    }

    private static Supplier<String> applyCompareAndSayLambda(int i, Function<Integer, Double> func, Predicate<Double> compare, String message){
        return () -> {
            double v = func.apply(i);
            return (compare.test(v)? v + " is " : v + " is not ") + message;
        };
    }

    @FunctionalInterface
    interface A {
        void method1();
        default void method2(){}
        static void method3(){}
    }

    @FunctionalInterface
    interface B extends A {
        default void method4(){}
    }

    @FunctionalInterface
    interface C extends B {
        void method1();
    }

    //@FunctionalInterface  //compilation error
    interface D extends C {
        void method5();
    }
}

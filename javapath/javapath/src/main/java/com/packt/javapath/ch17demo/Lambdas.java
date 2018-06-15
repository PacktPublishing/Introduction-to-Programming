package com.packt.javapath.ch17demo;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambdas {
    public static void main(String... args) {
        supplyDecideProcessAndConsume1();
        supplyDecideProcessAndConsume2();
        localVariable1();
        localVariable2();
        thisDemo();
        new Lambdas().methodReference1();
        new Lambdas().methodReference2();
        new Lambdas().methodReference3();

    }

    private void methodReference3() {
        supplyDecideProcessAndConsume(this::generateInput, Lambdas::checkValue, new Helper()::calculateResult, Helper::printResult);
    }

    private void methodReference2() {
        Supplier<Integer> input = this::generateInput;
        Predicate<Integer> test = Lambdas::checkValue;
        Function<Integer, Double> multiplyByFive = new Helper()::calculateResult;;
        Consumer<Double> printResult = Helper::printResult;
        supplyDecideProcessAndConsume(input, test, multiplyByFive, printResult);
    }

    private void methodReference1() {
        Supplier<Integer> input = () -> generateInput();
        Predicate<Integer> test = d -> checkValue(d);
        Function<Integer, Double> multiplyByFive = i -> new Helper().calculateResult(i);;
        Consumer<Double> printResult = d -> Helper.printResult(d);
        supplyDecideProcessAndConsume(input, test, multiplyByFive, printResult);
    }

    private int generateInput(){
        // Maybe many lines of code here
        return 7;
    }
    private static boolean checkValue(double d){
        // Maybe many lines of code here
        return d < 5;
    }

    private static void thisDemo() {
        ThisDemo d = new ThisDemo();
        d.useAnonymousClass();   //prints: AnonymousClassConsumer.field
        d.useLambdaExpression(); //prints: ThisDemo.field
    }

    private static class ThisDemo {
        private String field = "ThisDemo.field";

        public void useAnonymousClass() {
            Consumer<String> consumer = new Consumer<>() {
                private String field = "AnonymousClassConsumer.field";

                public void accept(String s) {
                    System.out.println(this.field);
                }
            };
            consumer.accept(this.field);
        }

        public void useLambdaExpression() {
            Consumer<String> consumer = consumer = s -> {
                System.out.println(this.field);
            };
            consumer.accept(this.field);
        }

    }

    private static class A {
        private int x;
        public int getX(){ return this.x; }
        public void setX(int x){ this.x = x; }
    }
    private static void localVariable2(){
        A a = new A();
        a.setX(7);
        a.setX(3);
        int y = 5;
        double z = 5.;
        supplyDecideProcessAndConsume(() -> a.getX(), d -> d < y, i -> i * z,
                d -> { a.setX(5);
                    System.out.println("Result=" + d + " Great!");
                });
    }

    private static void localVariable1(){
        int x = 7;
        //x = 3;    //compilation error
        int y = 5;
        double z = 5.;
        supplyDecideProcessAndConsume(() -> x, d -> d < y, i -> i * z,
                                  d -> { //x = 3;      //compilation error
                                         System.out.println("Result=" + d + " Great!");
                                  });
    }

    private static void supplyDecideProcessAndConsume2(){
        Consumer<Double> printResult = d -> System.out.println("Result=" + d + " Great!");
        supplyDecideProcessAndConsume(() -> 7, d -> d < 5, i -> i * 5., printResult);
    }

    private static void supplyDecideProcessAndConsume1(){
        Supplier<Integer> input = () -> 7;
        Predicate<Integer> test = d -> d < 5.;
        Function<Integer, Double> multiplyByFive = i -> i * 5.;;
        Consumer<Double> printResult = d -> System.out.println("Result=" + d + " Great!");
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

    Function<Integer, Double> createMultiplyBy(double num){
        Function<Integer, Double> func = i -> i * num;
        return func;
    }

    Consumer<Double> createPrintingFunc(String prefix, String postfix){
        Consumer<Double> func = d -> System.out.println(prefix + d + postfix);
        return func;
    }

    Supplier<Integer> createSuppplier(int num){
        Supplier<Integer> func = () -> num;
        return func;
    }

    Predicate<Integer> createTestSmallerThan(int num){
        Predicate<Integer> func = d -> d < num;
        return func;
    }
}

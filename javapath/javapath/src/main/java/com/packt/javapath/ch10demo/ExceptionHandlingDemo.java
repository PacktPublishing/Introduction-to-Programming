package com.packt.javapath.ch10demo;

public class ExceptionHandlingDemo {
    public static void main(String... args) {
        exceptionCaught();
        exceptionCaught2();
        try{
            exceptionNotCaught();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private class MyNpe extends NullPointerException{
        public MyNpe(String message){
            super(message);
        }
        //whatever code you need to have here
    }

    private class MyRuntimeException extends RuntimeException{
        public MyRuntimeException(String message){
            super(message);
        }
        //whatever code you need to have here
    }

    private class MyThrowable extends Throwable{
        public MyThrowable(String message){
            super(message);
        }
        //whatever code you need to have here
    }

    private class MyException extends Exception{
        public MyException(String message){
            super(message);
        }
        //whatever code you need to have here
    }

    private static void exceptionNotCaught() throws Exception{
        method2();
    }

    private static void exceptionCaught(){
        System.out.println();
        Exception exf = null;
        try {
            method2();
        } catch (NullPointerException ex){
            exf = ex;
            System.out.println("NPE caught");
        } catch (RuntimeException ex){
            exf = ex;
            System.out.println("RuntimeException caught");
        } catch (Exception ex){
            exf = ex;
            System.out.println("Exception caught");
        } finally {
            if(exf != null){
                exf.printStackTrace();
            }
        }
    }

    private static void exceptionCaught2(){
        System.out.println();
        try {
            method2_1();
        } catch (NullPointerException ex){
            System.out.println("NPE caught");
            ex.printStackTrace();
        } catch (RuntimeException ex){
            System.out.println("RuntimeException caught");
            ex.printStackTrace();
        } catch (Exception ex){
            System.out.println("Exception caught");
            ex.printStackTrace();
        }
    }

    private static void exceptionCaught1(){
        try {
            method2();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void method2() throws Exception{
        method1(null);
    }

    private static void method2_1(){
        method1_1(null);
    }

    private static void method1(String s) throws Exception{
        //throw null;
        //s.equals("whatever");
        //throw new NullPointerException("Parameter String is null");
        //throw new RuntimeException("Parameter String is null");
        //throw new ArrayIndexOutOfBoundsException("Index ... is bigger than the array length ...");
        throw new InstantiationException("No value for the field someField of SomeClass.");
    }

    private static void method1_1(String s){
        //throw null;
        //s.equals("whatever");
        //throw new NullPointerException("Parameter String is null");
        throw new RuntimeException("Parameter String is null");
    }
}

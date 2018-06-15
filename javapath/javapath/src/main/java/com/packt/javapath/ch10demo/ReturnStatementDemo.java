package com.packt.javapath.ch10demo;

public class ReturnStatementDemo {
    public static void main(String... args) {
        System.out.println(methodWithManyReturns());
    }

    public Integer methodBoxing(){
        return 42;
    }

    public int methodUnboxing(){
        return Integer.valueOf(42);
    }

    public int methodWidening(){
        byte b = 42;
        return b;
    }

    public byte methodNarrowing(){
        int n = 42;
        return (byte)n;
    }


    public boolean method01(int n){
        if(n < 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean sameAsMethod01(int n){
        if(n < 0) {
            return true;
        }
        return false;
    }

    public boolean sameAsAbove(int n){
        return n < 0 ? true : false;
    }

    public int method02(int n){
        if(n < 0) {
            return 1;
        } else if(n == 0) {
            return 2;
        } else if (n == 1){
            return 3;
        } else {
            return 4;
        }
    }
    public int methodSameAsMethod02(int n){
        if(n < 0) {
            return 1;
        }
        switch(n) {
            case 0:
                return 2;
            case 1:
                return 3;
            default:
                return 4;
        }
    }


    public static String methodWithManyReturns(){
        if(true){
            return "The only one returned";
        }
        if(true){
            return "Is never reached";
        }
        return "Is never reached";
    }

    public String method(int n){
        if(n == 1){
            return "One";
        } else {
            return "Not one";
        }
    }

    private static class ConstructorDemo{
        private int field;
        public ConstructorDemo(int i) {
            this.field = i;
            return;
        }
    }
}

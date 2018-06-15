package com.packt.javapath.ch06demo;

public class Ch06DemoApp {
    public static void main(String[] args) {

        System.out.println(B.NAME);
        System.out.println(B.m(""));
        System.out.println(new B.Clazz().m());

        System.out.println(ClassD.field);
        System.out.println(ClassD.m(""));
        System.out.println(new ClassD().field);
        System.out.println(new ClassD().m(""));
        ClassC object = new ClassD();
        System.out.println(object.field);
        System.out.println(object.m(""));

        System.out.println();

        System.out.println(new ClassD().field1);
        System.out.println(new ClassD().m1(""));
        ClassC object1 = new ClassD();
        System.out.println(object1.m1(""));
        System.out.println(object1.field1);
        System.out.println(((ClassD)object1).field1);

    }

    interface A extends B {
        String NAME = "interface A";
        static int m(String d) { return 42; }
        class Clazz{
            String m(){ return "Clazz A";}
        }
    }
    interface B {
        String NAME = "interface B";
        static int m(String d) { return 1; }
        class Clazz{
            String m(){ return "Clazz B";}
        }
    }

    interface C extends D{
        //@Override
        static int m(String d) { return 42; }
    }
    interface D {
        static int m(String s) { return 1; }
    }

    static class ClassC {
        public static String field = "static field C";
        public static String field1 = "instance field C";
        public static String m(String s){
            return "static method C";
        }
        public String m1(String s){
            return "instance method C";
        }
    }

    static class ClassD extends ClassC {
        public static String field = "static field D";
        //public static int field = 42;
        public String field1 = "instance field D";
        //public int field1 = 42;
        public static String m(String s){
            return "static method D";
        }
        public String m1(String s){
            return "instance method D";
        }
    }


    static class ClassA implements A{
        public static String field = "field A";
        public String m(String s){
            return "Class A";
        }
    }

    static class ClassB extends ClassA {
        //public String field = "field B";
        public int field = 42;
        public String m(String s){
            return "Class B";
        }
    }

}
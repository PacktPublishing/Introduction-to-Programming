package com.packt.javapath.ch06demo.fnal;

public class FinalDemo {
    final static SomeClass OBJ = new SomeClass();
    final static String S1 = "Initial value";
    final static String S2;
    final static int INT1 = 1;
    final static int INT2;

    static {
        INT2 = 2;
        S2 = "new value";
    }

    void method2(){
        OBJ.setSomeValue("new value");
        //OBJ = new SomeClass();
        //S1 = "";
        //S2 = "";
        //INT1 = 0;
        //INT2 = 0;
    }


    final SomeClass o = new SomeClass();
    final String s1 = "Initial value";
    final String s2;
    final String s3;
    final int i = 1;
    final int j;
    final int k;

    {
        j = 2;
        s2 = "new value";
    }

    FinalDemo() {
        k = 3;
        s3 = "new value";
    }

    public void method(){
        //this.i = 4;
        //this.j = 4;
        //this.k = 4;
        //this.s3 = "";
        this.o.setSomeValue("New value");
    }
    public static void main(String... args){
        final SomeClass o = new SomeClass();
        System.out.println(o.getSomeValue());
        o.setSomeValue("Another value");
        System.out.println(o.getSomeValue());
        o.setSomeValue("Yet another value");
        System.out.println(o.getSomeValue());

        final String s1, s2;
        final int x, y;
        y = 2;
        int v = y + 2;
        x = v - 4;
        s1 = "1";
        s2 = s1 + " and 2";
        System.out.println("x = " + x);
        System.out.println(s2);
        //s2 = "3";
        //x = 5;
        //y = 6;
        //o = new SomeClass();

        System.out.println();

        FinalVariable finalVar = new FinalVariable();
        System.out.println("Initial setting: finalVar.getInt()=" + finalVar.getInt());
        finalVar.setInt(5);
        System.out.println("After setting to 5: finalVar.getInt()=" + finalVar.getInt());
    }

    static class SomeClass{
        private String someValue = "Initial value";
        public void setSomeValue(String someValue) {
            this.someValue = someValue;
        }
        public String getSomeValue() {
            return someValue;
        }
    }

    static class FinalVariable {
        private int i;

        public FinalVariable() {
            this.i = 1;
        }

        public void setInt(final int i){
            this.i = 100;
            //i = i;
            this.i = i;
        }

        public int getInt(){
            return this.i;
        }
    }

}

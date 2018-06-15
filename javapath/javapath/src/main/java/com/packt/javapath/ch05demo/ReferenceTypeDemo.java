package com.packt.javapath.ch05demo;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.concurrent.TimeUnit;

public class ReferenceTypeDemo {

    public static void main(String[] args) throws Exception{

        int[][] is = new int[2][3];
        System.out.println("\nis.length=" + is.length);
        System.out.println("is[0].length=" + is[0].length);
        System.out.println("is[0][0].length=" + is[0][0]);
        System.out.println("is[0][1].length=" + is[0][1]);
        System.out.println("is[0][2].length=" + is[0][2]);
        System.out.println("is[1].length=" + is[0].length);
        System.out.println("is[1][0].length=" + is[1][0]);
        System.out.println("is[1][1].length=" + is[1][1]);
        System.out.println("is[1][2].length=" + is[1][2]);

        is = new int[2][];
        System.out.println("\nis.length=" + is.length);
        System.out.println("is[0]=" + is[0]);
        System.out.println("is[1]=" + is[1]);
        is[0] = new int[3];
        is[1] = new int[3];

        System.out.println("\nis.length=" + is.length);
        System.out.println("is[0].length=" + is[0].length);
        System.out.println("is[0][0].length=" + is[0][0]);
        System.out.println("is[0][1].length=" + is[0][1]);
        System.out.println("is[0][2].length=" + is[0][2]);
        System.out.println("is[1].length=" + is[0].length);
        System.out.println("is[1][0].length=" + is[1][0]);
        System.out.println("is[1][1].length=" + is[1][1]);
        System.out.println("is[1][2].length=" + is[1][2]);

        System.out.println("\ns1" + "s2");
        String s1 = "s1";
        System.out.println(s1 + "s2");
        String s2 = "s1";
        System.out.println(s1 + s2);

        System.out.println("s1" == "s1");
        System.out.println("s1" == "s2");
        String s11 = "s1";
        System.out.println(s11 == "s1");
        System.out.println(s1 == "s2");
        String s12 = "s1";
        System.out.println(s1 == s12);

        System.out.println("\nFirst line.\nSecond line.");
        System.out.println("Tab space\tin the line");
        System.out.println("It is called a \"String literal\".");
        System.out.println("Latin Capital Letter Y with diaeresis: \u0178");

        System.out.println();
        String s3 = new String("s");
        String s4 = new String("s");
        System.out.println(s3 == s4);

        System.out.println();
        System.out.println("s5".equals("s5"));
        System.out.println("s5".equals("s6"));
        String s5 = "s5";
        System.out.println(s5.equals("s5"));
        System.out.println(s5.equals("s6"));
        String s6 = "s6";
        System.out.println(s5.equals(s5));
        System.out.println(s5.equals(s6));
        String s7 = "s6";
        System.out.println(s7.equals(s6));
        String s8 = new String("s6");
        System.out.println(s8.equals(s7));
        String s9 = new String("s9");
        System.out.println(s8.equals(s9));

        System.out.println();
        enumDemo(Season.WINTER);
        enumDemo(Season.SPRING);

        System.out.println();
        Season season = Season.WINTER;
        System.out.println(Season.WINTER == season);
        System.out.println(Season.WINTER.equals(season));

        System.out.println();
        System.out.println(Season.SPRING.name());
        System.out.println(Season.SUMMER.ordinal());
        System.out.println(Enum.valueOf(Season.class, "AUTUMN"));
        System.out.println(Season.WINTER);

        System.out.println();
        System.out.println(Season1.SPRING.name());
        System.out.println(Season1.SUMMER.ordinal());
        System.out.println(Enum.valueOf(Season1.class, "AUTUMN"));
        System.out.println(Season1.WINTER);

        System.out.println();
        System.out.println(Season2.SPRING.name());
        System.out.println(Season2.SUMMER.ordinal());
        System.out.println(Enum.valueOf(Season2.class, "AUTUMN"));
        System.out.println(Season2.WINTER);

        System.out.println();
        enumDemo2(Season3.SPRING);
        enumDemo2(Season3.SUMMER);
        enumDemo2(Season3.AUTUMN);
        enumDemo2(Season3.WINTER);

        Month month = Month.FEBRUARY;
        TimeUnit timeUnit = TimeUnit.DAYS;
        DayOfWeek dayOfWeek = DayOfWeek.FRIDAY;
        Color color = Color.GREEN;
        Color color2 = Color.green;

        float f = 1.0f;
        SomeClass someClass = new SomeClass();
        System.out.println("\nBefore demoMethod(): f = " + f + ", count = " + someClass.getCount());
        demoMethod(f, someClass);
        System.out.println("After demoMethod(): f = " + f + ", count = " + someClass.getCount());

        int[] someArray = {1, 2, 3};
        System.out.println("\nBefore demoMethod(): someArray[0] = " + someArray[0]);
        demoMethod(someArray);
        System.out.println("After demoMethod(): someArray[0] = " + someArray[0]);

        String someString = "Some string";
        System.out.println("\nBefore demoMethod(): string = " + someString);
        demoMethod(someString);
        System.out.println("After demoMethod(): string = " + someString);

        int x = 'x';
        //int x1 = "x";
        //char x2 = "x";
        char x4 = 1;
        //String x3 = 1;
        //Month.MAY = 5;
        Month month1 = Month.APRIL;


    }

    enum Season {
        SPRING, SUMMER, AUTUMN, WINTER;
    }

    enum Season1 {
        SPRING, SUMMER, AUTUMN, WINTER;
        public String toString() {
            return "The best season";
        }
    }

    enum Season2 {
        SPRING,
        SUMMER,
        AUTUMN,
        WINTER { public String toString() { return "Winter"; } };
        public String toString() {
            return "The best season";
        }
    }

    public enum Season3 {
        SPRING("Spring", "warmer than winter", 60),
        SUMMER("Summer", "the hottest season", 100),
        AUTUMN("Autumn", "colder than summer", 70),
        WINTER("Winter", "the coldest season", 40);
        private String feel, toString;
        private int averageTemperature;
        Season3(String toString, String feel, int t) {
            this.feel = feel;
            this.toString = toString;
            this.averageTemperature = t;
        }
        public String getFeel(){
            return this.feel;
        }
        public int getAverageTemperature(){
            return this.averageTemperature;
        }
        public String toString() {
            return this.toString;
        }
    }

    private static void enumDemo(Season season){
        if(season == Season.WINTER){
            System.out.println("Dress up warmer");
        } else {
            System.out.println("You can drees up lighter now");
        }
    }

    private static void enumDemo2(Season3 season){
        System.out.println(season + " is " + season.getFeel());
        System.out.println(season + " has average temperature around " + season.getAverageTemperature());
    }

    private static void demoMethod(float fl, SomeClass someClass){
        //use fl and someClass to do something
        fl = 42.0f;
        someClass.setCount(42);
        someClass = new SomeClass();
        someClass.setCount(1001);
    }

    private static void demoMethod(int[] someArray){
        someArray[0] = 42;
        someArray = new int[3];
        someArray[0] = 43;
    }

    private static void demoMethod(String someString){
        someString = "Some other string";
    }

    private static class SomeClass implements SomeInterface {
        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    private interface SomeInterface {
        int getCount();
    }
}


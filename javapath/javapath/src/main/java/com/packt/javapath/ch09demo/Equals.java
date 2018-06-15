package com.packt.javapath.ch09demo;

import java.util.Objects;

public class Equals {
    public static void main(String[] args) {

        PersonNoEquals p1 = new PersonNoEquals(42, "Nick");
        PersonNoEquals p2 = new PersonNoEquals(42, "Nick");
        PersonNoEquals p3 = new PersonNoEquals(25, "Nick");
        System.out.println(p1.equals(p2));     //false
        System.out.println(p1.equals(p3));     //false
        System.out.println(p1 == p2);          //false
        p1 = p2;
        System.out.println(p1.equals(p2));     //true
        System.out.println(p1 == p2);          //true


        PersonWithEquals p11 = new PersonWithEquals(42, "Kelly", "Ponytail");
        PersonWithEquals p12 = new PersonWithEquals(42, "Kelly", "Pompadour");
        PersonWithEquals p13 = new PersonWithEquals(25, "Kelly", "Ponytail");
        System.out.println(p11.equals(p12));    //true
        System.out.println(p11.equals(p13));    //false
        System.out.println(p11 == p12);         //false
        p11 = p12;
        System.out.println(p11.equals(p12));    //true
        System.out.println(p11 == p12);         //true

        PersonWithHair p21 = new PersonWithHair(42, "Kelly", "Ponytail");
        PersonWithHair p22 = new PersonWithHair(42, "Kelly", "Pompadour");
        PersonWithHair p23 = new PersonWithHair(25, "Kelly", "Ponytail");
        System.out.println(p21.equals(p22));    //true
        System.out.println(p21.equals(p23));    //false
        System.out.println(p21 == p22);         //false
        p21 = p22;
        System.out.println(p21.equals(p22));    //true
        System.out.println(p21 == p22);         //true

        Person p31 = new PersonWithHair(42, "Kelly", "Ponytail");
        Person p32 = new PersonWithHairDressed(42, "Kelly", "Pompadour", "Suit");
        Person p33 = new PersonWithHair(25, "Kelly", "Ponytail");
        System.out.println(p31.equals(p32));    //true
        System.out.println(p31.equals(p33));    //false
        System.out.println(p31 == p32);         //false

        long ln = 42;
        Integer n = 42;
        System.out.println(n.equals(42));      //true
        System.out.println(n.equals(ln));      //false
        System.out.println(n.equals(43));      //false

        System.out.println(n.equals(Integer.valueOf(42)));  //true
        System.out.println(n.equals(Long.valueOf(42)));     //false

        String sl1 = "test1";
        String sl2 = "test2";
        String sl3 = "test1";

        System.out.println(sl1 == sl2);              //1: false
        System.out.println(sl1.equals(sl2));         //2: false

        System.out.println(sl1 == sl3);              //3: true
        System.out.println(sl1.equals(sl3));         //4: true

        String s1 = new String("test1");
        String s2 = new String("test2");
        String s3 = new String("test1");

        System.out.println(s1 == s2);                //5: false
        System.out.println(s1.equals(s2));           //6: false

        System.out.println(s1 == s3);                //7: false
        System.out.println(s1.equals(s3));           //8: true

        System.out.println(sl1 == s1);               //9: false
        System.out.println(sl1.equals(s1));          //10: true


    }

    private static class PersonNoEquals {
        private int age;
        private String name;

        public PersonNoEquals(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    private static class PersonWithEquals{
        private int age;
        private String name;
        private String hairstyle;

        public PersonWithEquals(int age, String name, String hairstyle) {
            this.age = age;
            this.name = name;
            this.hairstyle = hairstyle;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PersonWithEquals person = (PersonWithEquals) o;
            return age == person.age &&
                    Objects.equals(name, person.name);
        }
    }

    private static class Person{
        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            //if (o == null || getClass() != o.getClass()) return false;
            if (o == null) return false;
            if(!(o instanceof Person)) return false;
            Person person = (Person)o;
            return age == person.age &&
                    Objects.equals(name, person.name);
        }

        @Override
        public int hashCode(){
            return Objects.hash(age, name);
        }
    }

    private static class PersonWithHair extends Person{
        private String hairstyle;

        public PersonWithHair(int age, String name, String hairstyle) {
            super(age, name);
            this.hairstyle = hairstyle;
        }
    }

    private static class PersonWithHairDressed extends PersonWithHair{
        private String dress;

        public PersonWithHairDressed(int age, String name, String hairstyle, String dress) {
            super(age, name, hairstyle);
            this.dress = dress;
        }
    }

}

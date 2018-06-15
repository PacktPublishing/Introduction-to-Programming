package com.packt.javapath.ch15demo;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class ManageObjects {

    public static void main(String... args){

        equal();
        hash();
        isNull();
        requireNotNull();
        checkIndex();
        compare();
        sort();
        toStringWhenNull();
        append();
        mode();
    }

    private static void mode(){
        String s = ObjectUtils.mode("s0", "s1", "s1");
        System.out.println(s);     //prints: s1

        s = ObjectUtils.mode("s0", "s1", "s2");
        System.out.println(s);     //prints: null

        s = ObjectUtils.mode("s0", "s1", "s2", "s1", "s2");
        System.out.println(s);     //prints: null

        s = ObjectUtils.mode(null);
        System.out.println(s);     //prints: null

        s = ObjectUtils.mode("s0", null, null);
        System.out.println(s);     //prints: null
    }

    private static void append(){
        String s = "s0 " + ObjectUtils.identityToString("s1");
        System.out.println(s);  //s0 java.lang.String@5474c6c

        StringBuffer sb = new StringBuffer();
        sb.append("s0");
        ObjectUtils.identityToString(sb, "s1");
        System.out.println(s);  //s0 java.lang.String@5474c6c
    }


    private static void toStringWhenNull(){
        List<String> list = new ArrayList<>(List.of("s0 "));
        list.add(null);
        for(String e: list){
            System.out.print(e);  //s0 null
        }
        System.out.println();
        for(String e: list){
            System.out.print(Objects.toString(e)); //s0 null
        }
        System.out.println();
        for(String e: list){
            System.out.print(Objects.toString(e, "element was null")); //s0 element was null
        }
    }

    private static void sort(){

        Person p1 = new Person(15, "Zoe");
        Person p2 = new Person(45, "Adam");
        Person p3 = new Person(37, "Bob");
        Person p4 = new Person(30, "Bob");
        Person p5 = new Person(25, null);
        //List<Person> list = new ArrayList<>(List.of(p1, p2, p3, p4));
        List<Person> list = new ArrayList<>(List.of(p1, p2, p3, p4, p5));
        list.add(null);
        System.out.println(list);  //[{15, Zoe}, {45, Adam}, {37, Bob}, {30, Bob}, {25, }, null]
        //Collections.sort(list);
        Collections.sort(list, Comparator.nullsLast(Comparator.naturalOrder()));
        //Collections.sort(list, Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println(list);  //[{45, Adam}, {30, Bob}, {37, Bob}, {15, Zoe}, {25, }, null]
    }

    private static void compare(){

        System.out.println(Integer.valueOf(3).compareTo(Integer.valueOf(3))); //prints: 0
        System.out.println(Integer.valueOf(3).compareTo(Integer.valueOf(4))); //prints: -1
        System.out.println(Integer.valueOf(3).compareTo(Integer.valueOf(5))); //prints: -1
        System.out.println(Integer.valueOf(5).compareTo(Integer.valueOf(4))); //prints: 1
        System.out.println(Integer.valueOf(5).compareTo(Integer.valueOf(3))); //prints: 1

        Comparator<String> compStr = Comparator.naturalOrder();
        System.out.println(compStr.compare("a", "c"));  //prints: -2

        Comparator<Integer> compInt = Comparator.naturalOrder();
        System.out.println(compInt.compare(3, 5));     //prints: -1

        //System.out.println(compStr.compare(null, "c"));  //NullPointerException

        int res = Objects.compare("a", "c", Comparator.naturalOrder());
        System.out.println(res);       //prints: -2

       // res = Objects.compare(null, "c", Comparator.naturalOrder());   //NullPointerException

        int diff = Objects.compare("a", "c", Comparator.naturalOrder());
        System.out.println(diff);  //prints: -2
        diff = Objects.compare("a", "c", Comparator.reverseOrder());
        System.out.println(diff);  //prints: 2
        diff = Objects.compare(3, 5, Comparator.naturalOrder());
        System.out.println(diff);  //prints: -1
        diff = Objects.compare(3, 5, Comparator.reverseOrder());
        System.out.println(diff);  //prints: 1
    }

    private static void checkIndex(){
        List<String> list = List.of("s0", "s1");

        try {
            Objects.checkIndex(3, list.size());
        } catch (IndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());  //prints: Index 3 out-of-bounds for length 2
        }

        try {
            Objects.checkFromIndexSize(1, 3, list.size());
        } catch (IndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());  //prints: Range [1, 1 + 3) out-of-bounds for length 2
        }

        try {
            Objects.checkFromToIndex(1, 3, list.size());
        } catch (IndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());  //prints: Range [1, 3) out-of-bounds for length 2
        }
    }

    private static void requireNotNull(){
        String object = null;

        try {
            Objects.requireNonNull(object);
        } catch (NullPointerException ex){
            System.out.println(ex.getMessage());  //prints: null
        }

        //String object = null;
        try {
            Objects.requireNonNull(object, "Parameter 'object' is null");
        } catch (NullPointerException ex){
            System.out.println(ex.getMessage());  //prints: Parameter 'object' is null
        }

        //String object = null;
        Supplier<String> msg1 = () -> {
            String msg = "Msg from db";
            //go to a database or .properties file
            // and grab the corresponding message
            return msg;

        };
        try {
            Objects.requireNonNull(object, msg1);
        } catch (NullPointerException ex){
            System.out.println(ex.getMessage());  //prints: Msg from db
        }

        Supplier<String> msg2 = () -> null;
        try {
            Objects.requireNonNull(object, msg2);
        } catch (NullPointerException ex){
            System.out.println(ex.getMessage());  //prints: null
        }

        Supplier<String> msg3 = null;
        try {
            Objects.requireNonNull(object, msg3);
        } catch (NullPointerException ex){
            System.out.println(ex.getMessage());  //prints: null
        }

        //String object = null;
        System.out.println(Objects.requireNonNullElse(object, "Default value"));   //prints: Default value

        try {
            Objects.requireNonNullElse(object, null);
        } catch (NullPointerException ex){
            System.out.println(ex.getMessage());  //prints: defaultObj
        }

        //String object = null;
        System.out.println(Objects.requireNonNullElseGet(object, msg1));   //prints: Msg from db

        try {
            System.out.println(Objects.requireNonNullElseGet(object, msg2));
        } catch (NullPointerException ex){
            System.out.println(ex.getMessage());  //prints: supplier.get()
        }

        try {
            System.out.println(Objects.requireNonNullElseGet(object, null));
        } catch (NullPointerException ex){
            System.out.println(ex.getMessage());  //prints: supplier
        }

    }

    private static void isNull(){
        String object = null;

        System.out.println(object == null);           //prints: true
        System.out.println(Objects.isNull(object));   //prints: true

        System.out.println(object != null);           //prints: false
        System.out.println(Objects.nonNull(object));  //prints: false
    }

    private static void hash(){
        System.out.println(Objects.hashCode("s1"));   //prints: 3614
        System.out.println(Objects.hash("s1"));  //prints: 3645

        System.out.println(Objects.hashCode(null));   //prints: 0
        System.out.println(Objects.hash(null));  //prints: 0
    }

    private static void equal(){

        String s1 = "s";
        String s2 = "s";
        System.out.println(Objects.equals(s1, s2));        //prints: true
        System.out.println(Objects.deepEquals(s1, s2));    //prints: true

        s1 = null;
        s2 = null;
        System.out.println(Objects.equals(s1, s2));        //prints: true
        System.out.println(Objects.deepEquals(s1, s2));    //prints: true

        Integer[] as1 = {1,2,3};
        Integer[] as2 = {1,2,3};
        System.out.println(Arrays.equals(as1, as2));        //prints: true
        System.out.println(Arrays.deepEquals(as1, as2));    //prints: true

        System.out.println(Objects.equals(as1, as2));        //prints: false
        System.out.println(Objects.deepEquals(as1, as2));    //prints: true

        Integer[][] aas1 = {{1,2,3},{1,2,3}};
        Integer[][] aas2 = {{1,2,3},{1,2,3}};
        System.out.println(Arrays.equals(aas1, aas2));       //prints: false
        System.out.println(Arrays.deepEquals(aas1, aas2));   //prints: true

        System.out.println(Objects.equals(aas1, aas2));       //prints: false
        System.out.println(Objects.deepEquals(aas1, aas2));   //prints: true

    }

    private static class Person implements Comparable<Person> {
        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
            //this.name = name == null ? "" : name;
        }

        public int getAge(){ return this.age; }

        public String getName(){ return this.name; }

        @Override
        public int compareTo(Person p){
            //int result = this.name.compareTo(p.getName());
            //int result = Objects.compare(this.name, p.getName(), Comparator.naturalOrder());
            //int result = Objects.compare(this.name, p.getName(), Comparator.reverseOrder());
            //int result = ObjectUtils.compare(this.name, p.getName());
            int result = ObjectUtils.compare(this.name, p.getName(), true);
            if (result != 0) {
                return result;
            }
            //return this.age - p.getAge();
            //return Objects.compare(this.age, p.getAge(), Comparator.naturalOrder());
            return ObjectUtils.compare(this.age, p.getAge());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if(!(o instanceof Person)) return false;
            Person person = (Person)o;
            //return age == person.getAge() &&
            //        Objects.equals(name, person.getName());
            return Objects.equals(age, person.getAge()) &&
                    Objects.equals(name, person.getName());
        }

        @Override
        public int hashCode(){
            return Objects.hash(age, name);
        }

        @Override
        public String toString() {
            //return "{" + age + ", " + name + "}";
            return "{" + age + ", " + Objects.toString(name, "") + "}";
        }
    }
}

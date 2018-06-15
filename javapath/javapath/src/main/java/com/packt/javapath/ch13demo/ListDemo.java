package com.packt.javapath.ch13demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class ListDemo {
    public static void main(String... args) {
        add();
        iterate();
        locate();
        retrieve();
        remove1();
        remove2();
        remove3();
        replace();
        replaceAll1();
        replaceAll2();
        sort1();
        sort2();
        diff1();
        diff2();
        toArray();
    }

    private static void diff2(){
        List<String> list1 = new ArrayList<>();
        list1.add("s1");
        list1.add("s1");
        list1.add("s2");
        list1.add("s3");
        list1.add("s4");

        List<String> list2 = new ArrayList<>();
        list2.add("s1");
        list2.add("s2");
        list2.add("s2");
        list2.add("s5");

        List<String> list = new ArrayList<>(list1);
        list.retainAll(list2);
        System.out.println(list);    //prints: [s1, s1, s2]

        list = new ArrayList<>(list2);
        list.retainAll(list1);
        System.out.println(list);    //prints: [s1, s2, s2]

    }
    private static void diff1(){

        List<String> list1 = new ArrayList<>();
        list1.add("s1");
        list1.add("s1");
        list1.add("s2");
        list1.add("s3");
        list1.add("s4");

        List<String> list2 = new ArrayList<>();
        list2.add("s1");
        list2.add("s2");
        list2.add("s2");
        list2.add("s5");

        List<String> list = new ArrayList<>(list1);
        list.removeAll(list2);
        System.out.println(list);    //prints: [s3, s4]

        list = new ArrayList<>(list2);
        list.removeAll(list1);
        System.out.println(list);    //prints: [s5]

    }

    private static void sort2(){
        List<Person> list = new ArrayList<>();
        list.add(new PersonWithHair(45, "Bill", "27 Main Street", "Pompadour"));
        list.add(new PersonWithHair(42, "Kelly","15 Middle Street",  "Ponytail"));
        list.add(new PersonWithHairDressed(34, "Kelly", "10 Central Square",  "Pompadour", "Suit"));
        list.add(new PersonWithHairDressed(25, "Courtney", "27 Main Street",  "Ponytail", "Tuxedo"));

        System.out.println();
        list.forEach(System.out::println);

        list.sort(Comparator.naturalOrder());
        System.out.println();
        list.forEach(System.out::println);

        list.sort(Comparator.reverseOrder());
        System.out.println();
        list.forEach(System.out::println);

        list.sort(Comparator.comparing(Person::getName));
        System.out.println();
        list.forEach(System.out::println);

        list.sort(Comparator.comparing(Person::getAge));
        System.out.println();
        list.forEach(System.out::println);

        list.sort(Comparator.comparing(Person::getName).thenComparing(Person::getAge));
        System.out.println();
        list.forEach(System.out::println);

    }

    private static void sort1(){
        List<String> list = new ArrayList<>();
        list.add("s3");
        list.add("s2");
        list.add("ab");
        //list.add(null); //throws NullPointerException for sorting
                          //     String.CASE_INSENSITIVE_ORDER
                          //     Comparator.naturalOrder()
                          //     Comparator.reverseOrder()
        list.add("a");
        list.add("Ab");
        System.out.println(list);                //[s3, s2, ab, a, Ab]

        list.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println(list);                //[a, ab, Ab, s2, s3]

        list.sort(Comparator.naturalOrder());
        System.out.println(list);               //[Ab, a, ab, s2, s3]

        list.sort(Comparator.reverseOrder());
        System.out.println(list);               //[Ab, a, ab, s2, s3]

        "s1".compareTo("s2");                   //does not handle null

        list.add(null);
        list.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println(list);              //[null, Ab, a, ab, s2, s3]

        list.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println(list);              //[Ab, a, ab, s2, s3, null]

        Comparator<String> comparator = (s1, s2) ->{
            String s = (s1 == null ? "null" : s1);
            return s.compareTo(s2);
        };
        list.sort(comparator);
        System.out.println(list);              //[Ab, a, ab, null, s2, s3]
    }

    private static void replaceAll2(){
        List<String> list = new ArrayList<>();
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s1");

        UnaryOperator<String> function = s -> s.toUpperCase();
        list.replaceAll(function);

        function = s -> ("S1".equals(s) ? "S5" : null);
        list.replaceAll(function);

        function = s -> "a";
        list.replaceAll(function);

        function = s -> {
            String result;
            //write here any code you need to get the value
            // for the variable result based in the value of s
            System.out.println(s);   //prints "a" four times
            result = "42";
            return result;
        };
        list.replaceAll(function);
    }

    private static void replaceAll1(){
        List<String> list = new ArrayList<>();
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s1");

        list.replaceAll(s -> s.toUpperCase());
        System.out.println(list);    //prints: [S1, S2, S3, S1]

        list.replaceAll(s -> ("S1".equals(s) ? "S5" : null));
        System.out.println(list);    //prints: [S5, null, null, S5]

        list.replaceAll(s -> "a");
        System.out.println(list);    //prints: [a, a, a, a]

        list.replaceAll(s -> {
            String result;
            //write here any code you need to get the value
            // for the variable result based in the value of s
            System.out.println(s);   //prints "a" four times
            result = "42";
            return result;
        });
        System.out.println(list);    //prints: [42, 42, 42, 42]
    }

    private static void replace(){
        List<String> list = new ArrayList<>();
        list.add("s1");
        list.add("s2");

        list.set(1, null);
        System.out.println(list);    //prints: [s1, null]
    }

    private static void remove1(){
        List<String> list = new ArrayList<>();
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s1");

        System.out.println(list.remove(1));   //prints: s2
        System.out.println(list);                    //prints: [s1, s3, s1]
        //System.out.println(list.remove(5));        //throws IndexOutOfBoundsException
        System.out.println(list.remove("s1"));    //prints: true
        System.out.println(list);                    //prints: [s3, s1]
        System.out.println(list.remove("s5"));    //prints: false
        System.out.println(list);                     //prints: [s3, s1]
    }

    private static void remove2(){
        List<String> list = new ArrayList<>();
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s1");

        System.out.println(list.removeAll(List.of("s1", "s2", "s5")));   //prints: true
        System.out.println(list);                                        //prints: [s3]
        System.out.println(list.removeAll(List.of("s5")));               //prints: false
        System.out.println(list);                                        //prints: [s3]
    }

    private static void remove3(){
        List<String> list = new ArrayList<>();
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s1");

        System.out.println(list.retainAll(List.of("s1", "s2", "s5")));   //prints: true
        System.out.println(list);                                        //prints: [s1, s2, s1]
        System.out.println(list.retainAll(List.of("s1", "s2", "s5")));   //prints: false
        System.out.println(list);                                        //prints: [s1, s2, s1]
        System.out.println(list.retainAll(List.of("s5")));               //prints: true
        System.out.println(list);                                        //prints: []
    }

    private static void retrieve(){
        List<String> list = new ArrayList<>();
        list.add("s1");
        list.add("s2");
        list.add("s3");

        System.out.println(list.get(1));       //prints: s2
        System.out.println(list.subList(0,2)); //prints: [s1, s2]
    }

    private static void locate(){
        List<String> list = new ArrayList<>();
        list.add("s1");
        list.add("s2");
        list.add("s1");

        System.out.println(list.contains("s1"));       //prints: true
        System.out.println(list.indexOf("s1"));        //prints: 0
        System.out.println(list.lastIndexOf("s1")); //prints: 2
    }

    private static void toArray(){
        List<String> list = new ArrayList<>();
        list.add("s1");
        list.add("s2");

        Object[] arr1 = list.toArray();
        for(Object o: arr1){
            System.out.print(o);       //prints: s1s2
        }

        System.out.println();

        String[] arr2 = list.toArray(new String[list.size()]);

        for(String s: arr2){
            System.out.print(s);     //prints: s1s2
        }

        System.out.println();

        Object[] arr3 = list.stream().toArray();
        for (Object o : arr3) {
            System.out.print(o);       //prints: s1s2
        }

        System.out.println();

        String[] arr4 = list.stream().toArray(String[]::new);
        for (String s : arr4) {
            System.out.print(s);       //prints: s1s2
        }

    }

    private static void iterate(){
        System.out.println();
        List list = new ArrayList();
        list.add(null);
        list.add(1);
        list.add("ss");
        list.add(new A());
        list.add(new B());
        list.forEach(System.out::println);

        for(Object o: list){
           System.out.println(o);
        }

        //Remove strings from the list
        for(Object o: list){
            System.out.println(o);
            if(o instanceof String){
                //list.remove(o);
            }
        }

        System.out.println(list);  //prints: [null, 1, ss, A, B]
        Iterator iter = list.iterator();
        while(iter.hasNext()){
            Object o = iter.next();
            if(o instanceof String){
                iter.remove();
            }
        }
        System.out.println(list);  //prints: [null, 1, A, B]
    }

    private static void add() {
        List list = new ArrayList();
        list.add(null);
        list.add(1);
        list.add("ss");
        list.add(new A());
        list.add(new B());
        System.out.println(list);  //prints: [null, 1, ss, A, B]
        list.add(2, 42);
        System.out.println(list);  //prints: [null, 1, 42, ss, A, B]

        List<Integer> list1 = new ArrayList<>();
        list1.add(null);
        list1.add(1);
        //list1.add("ss");          //compilation error
        //list1.add(new A());       //compilation error
        //list1.add(new B());       //compilation error
        System.out.println(list1);  //prints: [null, 1]
        list1.add(2, 42);
        System.out.println(list1);  //prints: [null, 1, 42]

        List<Object> list2= new ArrayList<>();
        list2.add(null);
        list2.add(1);
        list2.add("ss");
        list2.add(new A());
        list2.add(new B());
        System.out.println(list2);    //prints: [null, 1, ss, A, B]
        list2.add(2, 42);
        System.out.println(list2);    //prints: [null, 1, 42, ss, A, B]

        list = list1;
        //list2 = list1;   //compilation error

        List<A> list3= new ArrayList<>();
        list3.add(null);
        //list3.add(1);            //compilation error
        //list3.add("ss");         //compilation error
        list3.add(new A());
        list3.add(new B());
        System.out.println(list3); //prints: [null, A, B]
        list3.add(2, new A());
        System.out.println(list3); //prints: [null, A, A, B]

        List<B> list4= new ArrayList<>();
        list4.add(null);
        //list4.add(1);            //compilation error
        //list4.add("ss");         //compilation error
        //list4.add(new A());      //compilation error
        list4.add(new B());
        System.out.println(list4); //prints: [null, B]
        list4.add(2, new B());
        System.out.println(list4); //prints: [null, B, B]

        List<?> list5= new ArrayList<>();
        list5.add(null);
        //list5.add(1);            //compilation error
        //list5.add("ss");         //compilation error
        //list5.add(new A());      //compilation error
        //list5.add(new B());      //compilation error
        System.out.println(list5); //prints: [null]
        //list5.add(1, 42);        //compilation error

        doSomething(list);
        System.out.println(list);  //[42, null, 1, 42, ss, A, B, null, 1, ss, A, B]

        doSomething1(list1);
        System.out.println(list1); //[42, null, 1, 42, null, 1]

        doSomething2(list2);
        System.out.println(list2); //[42, null, 1, 42, ss, A, B, null, 1, ss, A, B]

        doSomething3(list3);
        System.out.println(list3); //[A, null, A, A, B, null, A, B]

        doSomething4(list4);
        System.out.println(list4); //[B, null, B, B, null, B]

        doSomething5(list5);
        System.out.println(list5); //[null, null]

        List list6 = List.of("1");
        //doSomething(list6);    //throws UnsupportedOperationException

    }

    private static  void doSomething(List list){
        list.add(null);
        list.add(1);
        list.add("ss");
        list.add(new A());
        list.add(new B());
        list.add(0, 42);
    }

    private static void doSomething1(List<Integer> list){
        list.add(null);
        list.add(1);
        //list.add("ss");         //compilation error
        //list.add(new A());      //compilation error
        //list.add(new B());      //compilation error
        list.add(0, 42);
    }

    private static void doSomething2(List<Object> list){
        list.add(null);
        list.add(1);
        list.add("ss");
        list.add(new A());
        list.add(new B());
        list.add(0, 42);
    }

    private static void doSomething3(List<A> list){
        list.add(null);
        //list.add(1);          //compilation error
        //list.add("ss");       //compilation error
        list.add(new A());
        list.add(new B());
        list.add(0, new A());
    }

    private static void doSomething4(List<B> list){
        list.add(null);
        //list.add(1);          //compilation error
        //list.add("ss");       //compilation error
        //list.add(new A());    //compilation error
        list.add(new B());
        list.add(0, new B());
    }

    private static void doSomething5(List<?> list){
        list.add(null);
        //list.add(1);            //compilation error
        //list.add("ss");         //compilation error
        //list.add(new A());      //compilation error
        //list.add(new B());      //compilation error
        //list.add(0, 42);        //compilation error
    }

    static class A {
        @Override
        public String toString() { return "A"; }
    }
    static class B extends A {
        @Override
        public String toString() { return "B"; }
    }

    private static class Person implements Comparable<Person> {
        private int age;
        private String name, address ;

        public Person(int age, String name, String address) {
            this.age = age;
            this.name = name == null ? "" : name;
            this.address = address;
        }

        public int getAge(){ return this.age; }

        public String getName(){ return this.name; }

        @Override
        public int compareTo(Person p){
            //return age - p.getAge();
            //return name.compareTo(p.getName());
            int result = this.name.compareTo(p.getName());
            if (result != 0) {
                return result;
            }
            return this.age - p.getAge();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if(!(o instanceof Person)) return false;
            Person person = (Person)o;
            return age == person.getAge() &&
                    Objects.equals(name, person.getName());
        }

        @Override
        public int hashCode(){
            return Objects.hash(age, name);
        }

        @Override
        public String toString() {
            return "Person{age=" + age +
                    ", name=" + name + "}";
        }
    }

    private static class PersonWithHair extends Person {
        private String hairstyle;

        public PersonWithHair(int age, String name, String address, String hairstyle) {
            super(age, name, address);
            this.hairstyle = hairstyle;
        }
    }

    private static class PersonWithHairDressed extends PersonWithHair{
        private String dress;

        public PersonWithHairDressed(int age, String name, String address, String hairstyle, String dress) {
            super(age, name, address, hairstyle);
            this.dress = dress;
        }
    }

}

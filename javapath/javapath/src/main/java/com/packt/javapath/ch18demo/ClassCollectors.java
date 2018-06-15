package com.packt.javapath.ch18demo;

import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassCollectors {
    public static void main(String... args) {
        toCollection();
        joinning();
        summing();
        partitioningBy();
        groupingBy();
    }

    private static void groupingBy() {
        List<Person> list = List.of(new Person(23, "Bob"),
                new Person(33, "Jim"),
                new Person(23, "Jill"),
                new Person(33, "Bill"));
        Map<Integer, List<Person>> map = list.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(map);  //{33=[Person{name:Jim,age:33}, Person{name:Bill,age:33}], 23=[Person{name:Bob,age:23}, Person{name:Jill,age:23}]}
    }

    private static void partitioningBy() {
        List<Person> list = List.of(new Person(23, "Bob"),
                new Person(33, "Jim"),
                new Person(28, "Jill"),
                new Person(27, "Bill"));
        Map<Boolean, List<Person>> map = list.stream().collect(Collectors.partitioningBy(p->p.getAge() > 27));
        System.out.println(map);  //{false=[Person{name:Bob,age:23}, Person{name:Bill,age:27}], true=[Person{name:Jim,age:33}, Person{name:Jill,age:28}]}
    }

    private static void summing() {
        List<Person> list = List.of(new Person(23, "Bob"),
                new Person(33, "Jim"),
                new Person(28, "Jill"),
                new Person(27, "Bill"));
        int sum = list.stream().collect(Collectors.summingInt(Person::getAge));
        System.out.println(sum);  //prints: 111

        IntSummaryStatistics stats = list.stream().collect(Collectors.summarizingInt(Person::getAge));
        System.out.println(stats);  //IntSummaryStatistics{count=4, sum=111, min=23, average=27.750000, max=33}
        System.out.println(stats.getCount());    //4
        System.out.println(stats.getSum());      //111
        System.out.println(stats.getMin());      //23
        System.out.println(stats.getAverage());  //27.750000
        System.out.println(stats.getMax());      //33
    }

    private static void joinning() {
        List<String> list = List.of("a", "b", "c", "d");
        String result = list.stream().collect(Collectors.joining());
        System.out.println(result);  //abcd

        result = list.stream().collect(Collectors.joining(", "));
        System.out.println(result);  //a, b, c, d

        result = list.stream().collect(Collectors.joining(", ", "The result: ", ""));
        System.out.println(result);  //The result: a, b, c, d

        result = list.stream().collect(Collectors.joining(", ", "The result: ", ". The End."));
        System.out.println(result);  //The result: a, b, c, d. The End.
    }

    private static void toCollection() {
        List<String> list = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println(list);  //prints: [a, b, c]

        Set<String> set = Stream.of("a", "a", "c").collect(Collectors.toSet());
        System.out.println(set);   //prints: [a, c]

        List<Person> persons = List.of(new Person(23, "Bob"),
                new Person(33, "Jim"),
                new Person(28, "Jill"),
                new Person(27, "Bill"));
        Map<String, Person> map = persons.stream().collect(Collectors.toMap(p->p.getName() + "-" + p.getAge(), p->p));
        System.out.println(map); //prints: {Bob-23=Person{name:Bob,age:23}, Bill-27=Person{name:Bill,age:27}, Jill-28=Person{name:Jill,age:28}, Jim-33=Person{name:Jim,age:33}}

        Set<Person> personSet = persons.stream().collect(Collectors.toCollection(HashSet::new));
        //Set<Person> personSet = new HashSet<>(persons);
        System.out.println(personSet);  //prints: [Person{name:Bill,age:27}, Person{name:Jim,age:33}, Person{name:Bob,age:23}, Person{name:Jill,age:28}]

    }

    private static class Person1 {
        private String name;
        private int age;

        public Person1(){}
        public String getName() { return this.name; }
        public void setName(String name) { this.name = name; }

        public int getAge() {return this.age; }
        public void setAge(int age) { this.age = age;}

        @Override
        public String toString() {
            return "Person{name:" + this.name + ",age:" + age + "}";
        }
    }

    private static class Person {
        private String name;
        private int age;

        public Person(int age, String name) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return this.name; }

        public int getAge() {
            return this.age;
        }

        @Override
        public String toString() {
            return "Person{name:" + this.name + ",age:" + age + "}";
        }
    }

}

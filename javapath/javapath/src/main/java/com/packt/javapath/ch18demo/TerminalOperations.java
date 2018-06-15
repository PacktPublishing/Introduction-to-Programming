package com.packt.javapath.ch18demo;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperations {
    public static void main(String... args) {
      forEach1();
      forEach2();
      new TerminalOperations().forEach3();
      count();
      match();
      findAnyOrFirst();
      optional();
      minMax();
      toArray();
      reduce();
      collect();
    }

    private static void collect(){
        List<Person> list = List.of(new Person(23, "Bob"),
                                    new Person(33, "Jim"),
                                    new Person(28, "Jill"),
                                    new Person(27, "Bill"));

        Person1 theOldest = list.stream().collect(Person1::new,
                (p1, p2) -> {
                    if(p1.getAge() < p2.getAge()){
                        p1.setAge(p2.getAge());
                        p1.setName(p2.getName());
                    }
                },
                (p1, p2) -> {System.out.println("Combiner is called!");}); //prints nothing
        System.out.println(theOldest);  //prints: Person{name:Jim,age:33}


        BiConsumer<Person1, Person> accumulator = (p1, p2) -> {
            if(p1.getAge() < p2.getAge()){
                p1.setAge(p2.getAge());
                p1.setName(p2.getName());
            }
        };
        BiConsumer<Person1, Person1> combiner = (p1, p2) -> {
            System.out.println("Combiner is called!"); //prints nothing
        };
        theOldest = list.stream().collect(Person1::new, accumulator, combiner);
        System.out.println(theOldest);  //prints: Person{name:Jim,age:33}

        BiConsumer<Person1, Person> accumulator1 = (p1, p2) -> {
            if(p1.getAge() < p2.getAge()){
                p1.setAge(p2.getAge());
                p1.setName(p2.getName());
            }
        };
        BiConsumer<Person1, Person1> combiner1 = (p1, p2) -> {
            System.out.println("Combiner is called!"); //prints 3 times
            if(p1.getAge() < p2.getAge()){
                p1.setAge(p2.getAge());
                p1.setName(p2.getName());
            }
        };
        theOldest = list.parallelStream().collect(Person1::new, accumulator1, combiner1);
        System.out.println(theOldest);  //prints: Person{name:Jim,age:33}
    }

    private static void reduce(){
        List<Person> list = List.of(new Person(23, "Bob"),
                                    new Person(33, "Jim"),
                                    new Person(28, "Jill"),
                                    new Person(27, "Bill"));
        Person theOldest = list.stream().reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2).orElse(null);
        System.out.println(theOldest);  //prints: Person{name:Jim,age:33}

        String allNames = list.stream().map(p->p.getName()).reduce((n1, n2) -> n1 + ", " + n2).orElse(null);
        System.out.println(allNames);   //prints: Bob, Jim, Jill, Bill

        allNames = list.stream().map(p->p.getName()).reduce("All names: ", (n1, n2) -> n1 + ", " + n2);
        System.out.println(allNames);   //All names: , Bob, Jim, Jill, Bill

        allNames = "All names: " + list.stream().map(p->p.getName()).reduce((n1, n2) -> n1 + ", " + n2).orElse(null);
        System.out.println(allNames);   //All names: Bob, Jim, Jill, Bill

        allNames = list.stream().map(p->p.getName()).reduce("All names:", (n1, n2) -> n1 + " " + n2);
        System.out.println(allNames);   //All names: Bob, Jim, Jill, Bill

        allNames = list.stream().map(p->p.getName()).reduce("All names:", (n1, n2) -> n1 + " " + n2, (n1, n2) -> n1 + " " + n2);
        System.out.println(allNames);   //All names: Bob, Jim, Jill, Bill

        allNames = list.parallelStream().map(p->p.getName()).reduce("All names:", (n1, n2) -> n1 + " " + n2);
        System.out.println(allNames);   //All names: Bob All names: Jim All names: Jill All names: Bill

        allNames = list.parallelStream().map(p->p.getName())
                .reduce("All names:", (n1, n2) -> n1 + " " + n2, (n1, n2) -> n1 + " " + n2);
        System.out.println(allNames);   //All names: Bob All names: Jim All names: Jill All names: Bill

        allNames = list.parallelStream().map(p->p.getName()).reduce("All names:", (n1, n2) -> n1 + " " + n2,
                (n1, n2) -> n1 + " " + StringUtils.remove(n2, "All names:"));
        System.out.println(allNames);   //All names: Bob, Jim, Jill, Bill

        List<Integer> ints = List.of(1, 2, 3);
        int sum = ints.stream().reduce((i1, i2) -> i1 + i2).orElse(0);
        System.out.println(sum);       //prints: 6

        sum = ints.stream().reduce(Integer::sum).orElse(0);
        System.out.println(sum);       //prints: 6

        sum = ints.stream().reduce(10, Integer::sum);
        System.out.println(sum);       //prints: 16

        sum = ints.stream().reduce(10, Integer::sum, Integer::sum);
        System.out.println(sum);       //prints: 16

        sum = ints.parallelStream().reduce(10, Integer::sum, Integer::sum);
        System.out.println(sum);       //prints: 36

        sum = ints.parallelStream().reduce(0, Integer::sum, Integer::sum);
        System.out.println(sum);       //prints: 6

        sum = 10 + ints.parallelStream().reduce(0, Integer::sum, Integer::sum);
        System.out.println(sum);       //prints: 16

    }

    private static void toArray() {
        List<String> list = List.of("a", "b", "c");
        Object[] obj = list.stream().toArray();
        Arrays.stream(obj).forEach(System.out::print);    //prints: abc

        System.out.println();
        String[] str = list.stream().toArray(String[]::new);
        Arrays.stream(str).forEach(System.out::print);    //prints: abc

        System.out.println();
        str = list.stream().toArray(i -> new String[i]);
        Arrays.stream(str).forEach(System.out::print);    //prints: abc

        System.out.println();
        IntFunction<String[]> intFunction = new IntFunction<String[]>() {
            @Override
            public String[] apply(int i) {
                return new String[i];
            }
        };
        str = list.stream().toArray(intFunction);
        Arrays.stream(str).forEach(System.out::print);    //prints: abc

        System.out.println();
        intFunction = i -> new String[i];
        str = list.stream().toArray(intFunction);
        Arrays.stream(str).forEach(System.out::print);    //prints: abc

        System.out.println();
        str = list.toArray(new String[list.size()]);
        Arrays.stream(str).forEach(System.out::print);    //prints: abc
    }

    private static void minMax() {
        List<String> list = List.of("a", "b", "c", "c", "a");
        String min = list.stream().min(Comparator.naturalOrder()).orElse("0");
        System.out.println(min);    //prints: a

        String max = list.stream().max(Comparator.naturalOrder()).orElse("0");
        System.out.println(max);    //prints: c

        int mn = Stream.of(42, 33, 77).min(Comparator.naturalOrder()).orElse(0);
        System.out.println(mn);    //prints: 33
        int mx = Stream.of(42, 33, 77).max(Comparator.naturalOrder()).orElse(0);
        System.out.println(mx);    //prints: 77

        Comparator<Person> perComp = (p1, p2) -> p1.getAge() - p2.getAge();
        List<Person> persons = List.of(new Person(23, "Bob"),
                new Person(33, "Jim"),
                new Person(28, "Jill"),
                new Person(27, "Bill"));
        Person theOldest = persons.stream().max(perComp).orElse(null);
        System.out.println(theOldest);  //prints: Person{name:Jim,age:33}
    }

    private static void optional() {
        List<String> list = List.of("1", "2", "3", "4", "5");

        String result = list.stream().filter(e -> "42".equals(e)).findAny().or(() -> Optional.of("Not found")).get();
        System.out.println(result);   //prints: Not found

        result = list.stream().filter(e -> "42".equals(e)).findAny().orElse("Not found");
        System.out.println(result);   //prints: Not found

        Supplier<String> trySomethingElse = () -> {
            //Code that tries something else
            return "43";
        };
        result = list.stream().filter(e -> "42".equals(e)).findAny().orElseGet(trySomethingElse);
        System.out.println(result);   //prints: 43

        list.stream().filter(e -> "42".equals(e)).findAny().ifPresentOrElse(System.out::println, () -> System.out.println("Not found"));
    }

    private static void findAnyOrFirst() {
        List<String> list = List.of("1", "2", "3", "4", "5");
        Optional<String> result = list.stream().findAny();
        System.out.println(result.isPresent());    //prints: true
        System.out.println(result.get());          //prints: 1

        result = list.stream().filter(e -> "42".equals(e)).findAny();
        System.out.println(result.isPresent());    //prints: true
        //System.out.println(result.get());        //NoSuchElementException

        result = list.stream().findFirst();
        System.out.println(result.isPresent());    //prints: true
        System.out.println(result.get());          //prints: 1
    }

    private static void match() {
        List<String> list = List.of("1", "2", "3", "4", "5");

        boolean found = list.stream()
                .peek(System.out::print)         //prints: 123
                .anyMatch(e -> "3".equals(e));
        System.out.print(found);                 //prints: true

        System.out.println();
        found = list.stream()
                .peek(System.out::print)         //prints: 12345
                .anyMatch(e -> "0".equals(e));
        System.out.print(found);                 //prints: false

        System.out.println();
        boolean noneMatches = list.stream()
                .peek(System.out::print)          //prints: 123
                .noneMatch(e -> "3".equals(e));
        System.out.print(noneMatches);            //prints: false

        System.out.println();
        noneMatches = list.stream()
                .peek(System.out::print)         //prints: 12345
                .noneMatch(e -> "0".equals(e));
        System.out.print(noneMatches);           //prints: true

        System.out.println();
        boolean allMatch = list.stream()
                .peek(System.out::print)         //prints: 1
                .allMatch(e -> "3".equals(e));
        System.out.print(allMatch);              //prints: false
    }

    private static void count() {
        System.out.println();
        long count = Stream.of("1", "2", "3", "4", "5")
                .peek(System.out::print)
                .count();
        System.out.print(count);                 //prints: 5

        System.out.println();
        count = Stream.of("1", "2", "3", "4", "5")
                .peek(System.out::print)         //prints: 12345
                .collect(Collectors.counting());
        System.out.println(count);                //prints: 5
    }

    private void forEach3(){
        System.out.println();
        List<Person> persons = new ArrayList<>();
        Path path = Paths.get("src/main/resources/persons.csv");
        try (Stream<String> lines = Files.newBufferedReader(path).lines()) {
            persons = lines.map(s -> s.split(","))
                    .map(this::createPerson)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        persons.stream().forEach(System.out::println);
    }

    private Person createPerson(String[] arr){
        int age = Integer.valueOf(StringUtils.remove(arr[0], ' '));
        return new Person(age, StringUtils.remove(arr[1], ' '));
    }

    private static void forEach2(){
        System.out.println();
        List<Person> persons = new ArrayList<>();
        Path path = Paths.get("src/main/resources/persons.csv");
        try (Stream<String> lines = Files.newBufferedReader(path).lines()) {
            persons = lines.map(s -> s.split(","))
                    .map(arr -> {
                        int age = Integer.valueOf(StringUtils.remove(arr[0], ' '));
                        return new Person(age, StringUtils.remove(arr[1], ' '));
                    })
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        persons.stream().forEach(System.out::println);
    }

    private static void forEach1(){
        System.out.println();
        List<Person> persons = new ArrayList<>();
        Path path = Paths.get("src/main/resources/persons.csv");
        try (Stream<String> lines = Files.newBufferedReader(path).lines()) {
            lines.forEach(s -> {
                String[] arr = s.split(",");
                int age = Integer.valueOf(StringUtils.remove(arr[0], ' '));
                persons.add(new Person(age, StringUtils.remove(arr[1], ' ')));
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        persons.stream().forEach(System.out::println);  //prints: Person{name='Jim', age=23}
                                                        //        Person{name='Bob', age=25}
                                                        //        Person{name='Jill', age=15}
                                                        //        Person{name='Bill', age=17}
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

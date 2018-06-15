package com.packt.javapath.ch14demo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ManageCollections {

    public static void main(String... args){

        initialize();
        immutable();
        classCollections_checked();
        classCollections_copyList();
        classCollections_sorting();
        classCollections_reverse();
        classCollections_search();
        classCollections_minmax();
        classCollections_addAll();

    }

    private static void classCollections_addAll() {
        Set<String> set = new HashSet<>(Arrays.asList("s1","s2"));
        Collections.addAll(set,"s1", "s3", "s3");
        System.out.println(set);

    }

    private static void classCollections_minmax(){
        Person p1 = new Person("Zoe", "Arnold");
        Person p2 = new Person("Alex", "Green");
        Person p3 = new Person("Maria", "Brown");
        List<Person> list7 = Arrays.asList(p1,p2,p3);
        System.out.println(list7);  //[Zoe Arnold, Alex Green, Maria Brown]

        System.out.println(Collections.min(list7)); //prints: Alex Green
        System.out.println(Collections.max(list7)); //prints: Zoe Arnold

        Person min = Collections.min(list7, new OrderByLastThenFirstName());
        System.out.println(min);                    //[Zoe Arnold]

        Person max = Collections.max(list7, new OrderByLastThenFirstName());
        System.out.println(max);                    //[Alex Green]

    }


    private static void classCollections_search(){
        List<String> list1 = List.of("s3","s5","s4","s1");
        List<String> list2 = List.of("s4","s5");
        int index = Collections.indexOfSubList(list1, list2);
        System.out.println(index);  //prints: -1

        List<String> list3 = List.of("s5","s4");
        index = Collections.indexOfSubList(list1, list3);
        System.out.println(index);   //prints: 1

        List<String> list4 = List.of("s3","s4","s4","s1");
        int count = Collections.frequency(list4, "s4");
        System.out.println(count);    //prints: 2

        List<A> list5 = List.of(new A(), new B());
        int c = Collections.frequency(list5, new A());
        System.out.println(c);         //prints: 0

        A a = new A();
        List<A> list6 = List.of(a, new B());
        c = Collections.frequency(list6, a);
        System.out.println(c);         //prints: 1
    }


    private static void classCollections_reverse(){
        Person p1 = new Person("Zoe", "Arnold");
        Person p2 = new Person("Alex", "Green");
        Person p3 = new Person("Maria", "Brown");
        List<Person> list7 = Arrays.asList(p1,p2,p3);
        System.out.println(list7);  //[Zoe Arnold, Alex Green, Maria Brown]

        Collections.reverse(list7);
        System.out.println(list7);  //[Maria Brown, Alex Green, Zoe Arnold]

        Collections.rotate(list7, 1);
        System.out.println(list7);  //[Zoe Arnold, Maria Brown, Alex Green]

        Collections.sort(list7, Collections.reverseOrder());
        System.out.println(list7);  //[Zoe Arnold, Maria Brown, Alex Green]

        Collections.sort(list7, new OrderByLastThenFirstName());
        System.out.println(list7);  //[Zoe Arnold, Maria Brown, Alex Green]

        Collections.sort(list7, Collections.reverseOrder(new OrderByLastThenFirstName()));
        System.out.println(list7);  //[Alex Green, Maria Brown, Zoe Arnold]

    }

    private static void classCollections_copyList(){
        List<String> list1 = Arrays.asList("s1","s2");
        List<String> list2 = Arrays.asList("s3", "s4", "s5");
        Collections.copy(list2, list1);
        System.out.println(list2);    //prints: [s1, s2, "s5"]
    }

    private static void classCollections_sorting(){

        List<String> no = Arrays.asList("a","b", "Z", "10", "20", "1", "2");
        Collections.sort(no);
        System.out.println(no);  //prints: [1, 10, 2, 20, Z, a, b]

        System.out.println("a".compareTo("c"));   //prints: -2
        System.out.println("c".compareTo("a"));   //prints: 2

        Person joe1 = new Person("Joe", "Smith");
        Person joe2 = new Person("Joe", "Smith");
        Person bob = new Person("Bob", "Smith");

        System.out.println(joe1.equals(joe2));    //prints: true
        System.out.println(joe1.compareTo(joe2)); //prints: 0

        System.out.println(joe1.equals(bob));     //prints: false
        System.out.println(joe1.compareTo(bob));  //prints: 8
        System.out.println(joe2.compareTo(bob));  //prints: 8

        Person p1 = new Person("Zoe", "Arnold");
        Person p2 = new Person("Alex", "Green");
        Person p3 = new Person("Maria", "Brown");
        List<Person> list7 = Arrays.asList(p1,p2,p3);
        System.out.println(list7);  //[Zoe Arnold, Alex Green, Maria Brown]
        Collections.sort(list7);
        System.out.println(list7);  //[Alex Green, Maria Brown, Zoe Arnold]

        Collections.sort(list7, new OrderByLastThenFirstName());
        System.out.println(list7);  //[Zoe Arnold, Maria Brown, Alex Green]
    }

    private static class OrderByLastThenFirstName implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2){
            int result = p1.getLastName().compareTo(p2.getLastName());
            if (result == 0) {
                return p1.getFirstName().compareTo(p2.getFirstName());
            }
            return result;
        }
    }

    //possible but not recommended way
    //causes overhead and possible problems with serializtion
    private List<String> list = new ArrayList<>() {
        {
            add(null);
            add("s2");
            add("s3");
        }
    };
    private List<String> list1 = Arrays.asList(null, "s2", "s3");

    private static Set<String> set = new HashSet<>();
    static {
        SomeClass someClass = new SomeClass();
        set.add(someClass.getThatString());
        set.add("another string");
    }

    private static Map<Integer, String> map = new HashMap<>();
    static {
        map.put(1, null);
        map.put(2, "s2");
    }

    public ManageCollections(){
        list.add(null);
        list.add("s2");
        list.add("s3");

    }

    private static void classCollections_checked(){
        List list = new ArrayList();
        list.add("s1");
        list.add("s2");
        list.add(42);
        System.out.println(list);   //prints: [s1, s2, 42]

        List cList = Collections.checkedList(list, String.class);
        System.out.println(list);   //prints: [s1, s2, 42]

        list.add(42);
        System.out.println(list);   //prints: [s1, s2, 42, 42]

        //cList.add(42);               //throws ClassCastException
    }

    private static void immutable(){
        List<String> list = Arrays.asList("s1", "s1");
        System.out.println(list);  //prints: [s1, s1]

        List<String> unmodfifiableList = Collections.unmodifiableList(list);
        //unmodfifiableList.set(0, "s1"); //UnsupportedOperationException
        //unmodfifiableList.add("s2");    //UnsupportedOperationException
        System.out.println(unmodfifiableList);      //prints: [s1, s1]

        list.set(0, "s0");
        //list.add("s2");       //UnsupportedOperationException
        System.out.println(unmodfifiableList);      //prints: [s0, s1]

        List<String> iList =
                Collections.unmodifiableList(new ArrayList<>() {{
                    add("s1");
                    add("s1");
                }});
        //iList.set(0, "s0");   //UnsupportedOperationException
        //iList.add("s2");      //UnsupportedOperationException
        System.out.println(iList);      //prints: [s1, s1]


        Map<Integer, String> iMap =
                Collections.unmodifiableMap(new HashMap<>() {{
                    put(1, "s1");
                    put(2, "s2");
                }});
        //iMap.put(1, "s0");   //UnsupportedOperationException
        //iMap.put(3, "s3");   //UnsupportedOperationException
        System.out.println(iMap);      //prints: {1=s1, 2=s2}


        String[] source = {"s1", "s2"};
        List<String> iList2 =
                Arrays.stream(source).collect(Collectors.toList());
        System.out.println(iList2);      //prints: [s1, s2]

        source[0]="s0";
        System.out.println(iList2);      //prints: [s1, s2]


        String[][] mapping =
                new String[][] {{"1", "s1"}, {"2", "s2"}};
        Map<Integer, String> iMap2 = Arrays.stream(mapping)
                        .collect(Collectors.toMap(a -> Integer.valueOf(a[0]),
                        a -> a[1] == null? "" : a[1]));
        System.out.println(iMap2);      //prints: {1=s1, 2=s2}

        mapping[0][0]="0";
        mapping[0][1]="1";
        System.out.println(iMap2);      //prints: {1=s1, 2=s2}


        List<String> list1 = Collections.EMPTY_LIST;
        //list1.add("s1");       //UnsupportedOperationException
        Set<String> set1 = Collections.EMPTY_SET;
        Map<Integer, String> map1 = Collections.EMPTY_MAP;

        List<String> list2 = Collections.emptyList();
        //list2.add("s1");       //UnsupportedOperationException
        Set<String> set2 = Collections.emptySet();
        Map<Integer, String> map2 = Collections.emptyMap();

        SortedSet<String> set3 = Collections.emptySortedSet();
        Map<Integer, String> map3 = Collections.emptySortedMap();
        NavigableSet<String> set4 = Collections.emptyNavigableSet();
        NavigableMap<Integer, String> map4 = Collections.emptyNavigableMap();

        List<String> singletonS1 = Collections.singletonList("s1");
        System.out.println(singletonS1);
        //singletonS1.add("s1");        //UnsupportedOperationException

        List<String> nList = Collections.nCopies(3, "s1");
        System.out.println(nList);
        //nList.add("s1");        //UnsupportedOperationException

    }

    private static void initialize(){

        ManageCollections mc = new ManageCollections();
        System.out.println(mc.getThatList());    //prints: [null, s2, s3]

        System.out.println(set);  //prints: [that string, another string]

        List<A> l1 = Arrays.asList(new B());
        //l1.add(new B());                      //UnsupportedOperationException
        Set<A> ss = new HashSet<>(l1);
        ss.add(new B());

        List<B> l2 = Arrays.asList(new B());
        //List<B> l3 = Arrays.asList(new A());  //compilation error

        List<String> iList0 = List.of();
        List<String> iList1 = List.of("s1");
        List<String> iList2 = List.of("s1", "s2");
        List<String> iList3 = List.of("s1", "s2", "s3");

        Set<String> iSet1 = Set.of("s1", "s2", "s3", "s4");
        Set<String> iSet2 = Set.of("s1", "s2", "s3", "s4", "s5");
        Set<String> iSet3 = Set.of("s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10");
        System.out.println();
        System.out.println(iSet3);
        System.out.println();

        Map<Integer, String> iMap = Map.of(1, "s1", 2, "s2", 3, "s3");

        Map<Integer, String> map = new HashMap<>();
        map.put(null, "s1");
        map.put(2, "s2");
        System.out.println(map.get(null));     //prints: s1

        List<String> list = Arrays.asList(null, "s2", "s3");
        Set<String> set = new HashSet<>(list);
        System.out.println(set);                 //prints: [null, s2, s3]

        List<String> list1 = new ArrayList<>();
        list1.addAll(set);
        System.out.println(list1);               //prints: [null, s2, s3]

        Set<String> set1 = new HashSet<>();
        set1.addAll(mc.getThatList());
        System.out.println(set1);                //prints: [null, s2, s3]

        Map<Integer, String> map1 = new HashMap<>();
        map1.putAll(map);
        System.out.println(map);                 //prints: {1=null, 2=s2}

        List<String> x1 = Arrays.asList(null, "s2", "s3");
        String[] array = {null, "s2", "s3"};
        List<String> x2 = Arrays.asList(array);
        System.out.println(x1.equals(x2));       //prints: true

        //There are no guarantees on the type
        List<String> list2 = Stream.of(null, "s2", "s3").collect(Collectors.toList());
        System.out.println(list2);               //prints: [null, s2, s3]

        List<String> list3 = Stream.of(null, "s2", "s3").collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list3);               //prints: [null, s2, s3]

        //There are no guarantees on the type
        Set<String> set2 = Stream.of(null, "s2", "s3").collect(Collectors.toSet());
        System.out.println(set2);               //prints: [null, s2, s3]

        Set<String> set3 = Stream.of(null, "s2", "s3").collect(Collectors.toCollection(HashSet::new));
        System.out.println(set3);               //prints: [null, s2, s3]

        Map<Integer, String> m = new HashMap<>();
        m.put(1, null);
        m.put(2, "s2");
        Map<Integer, String> map2 = m.entrySet().stream()
                .map(e -> e.getValue() == null ? Map.entry(e.getKey(), "") : e)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(map2);               //prints: {1=, 2=s2}

        Map<Integer, String> map3 = m.entrySet().stream()
                .map(e -> e.getValue() == null ? Map.entry(e.getKey(), "") : e)
                .collect(Collectors.toMap(e->e.getKey(), e->e.getValue(),(k,v)->v, HashMap::new));
        System.out.println(map3);               //prints: {1=, 2=s2}
    }

    public List<String> getThatList(){
        return this.list;
    }

    private static class SomeClass{
        public String getThatString(){
            return "that string";
        }
    }

    static class A {
        @Override
        public String toString() { return "A"; }

/*
        @Override
        public boolean equals(Object o){
            if (o == null) return false;
            return o instanceof A;
            //return o.getClass().equals(this.getClass());
        }
*/

    }
    static class B extends A {
        @Override
        public String toString() { return "B"; }
    }

    static class Person implements Comparable<Person>{
        private String firstName = "", lastName = "";

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        @Override
        public int compareTo(Person person){
            int result = this.firstName.compareTo(person.firstName);
            if (result == 0) {
                return this.lastName.compareTo(person.lastName);
            }
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) return false;
            if (this == other) return true;
            if (!(other instanceof Person)) return false;
            final Person that = (Person) other;
            return this.firstName.equals(that.getFirstName()) &&
                    this.lastName.equals(that.getLastName());
        }

        @Override
        public String toString(){
            return this.firstName + " " + this.lastName;
        }

    }

}

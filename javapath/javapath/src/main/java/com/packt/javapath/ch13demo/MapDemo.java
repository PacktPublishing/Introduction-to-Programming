package com.packt.javapath.ch13demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {
    public static void main(String... args) {
        add();
        iterate();
        addMap();
        retrieve();
        remove();
        removeByKey();
        replace();
        replaceUsingFunction();
        equals();
    }

    private static void equals(){
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, null);
        map1.put(2, "s2");
        map1.put(3, "s3");

        Map<Integer, String> map2 = new HashMap<>();
        map2.put(1, null);
        map2.put(2, "s2");
        map2.put(3, "s3");

        System.out.println(map2.equals(map1)); //prints: true

        map2.put(1, "s1");
        System.out.println(map2.equals(map1)); //prints: false

        map1.entrySet().containsAll(map2.entrySet());
        map1.entrySet().retainAll(map2.entrySet());
        map1.entrySet().removeAll(map2.entrySet());

        map1.keySet().containsAll(map2.keySet());
        map1.keySet().retainAll(map2.keySet());
        map1.keySet().removeAll(map2.keySet());

        map1.values().containsAll(map2.values());
        map1.values().retainAll(map2.values());
        map1.values().removeAll(map2.values());
    }

    private static void replaceUsingFunction(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, null);
        map.put(2, null);
        map.put(3, "s3");

        map.replaceAll((k,v) -> v == null? "s" + k : v);
        System.out.println(map);                    //prints: {1=s1, 2=s2, 3=s3}

        map.replaceAll((k,v) -> k == 2? "n2" : v);
        System.out.println(map);                    //prints: {1=s1, 2=n2, 3=s3}

        map.replaceAll((k,v) -> v.startsWith("s") ? "s" + (k + 10) : v);
        System.out.println(map);                    //prints: {1=s11, 2=n2, 3=s13}

    }

    private static void replace(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, null);
        map.put(2, "s2");
        map.put(3, "s3");

        System.out.println(map.replace(1, "s1"));   //prints: null
        System.out.println(map);                    //prints: {1=s1, 2=s2, 3=s3}

        System.out.println(map.replace(4, "s1"));   //prints: null
        System.out.println(map);                    //prints: {1=s1, 2=s2, 3=s3}

        System.out.println(map.replace(1, "s2", "s1"));   //prints: false
        System.out.println(map);                    //prints: {1=s1, 2=s2, 3=s3}

        System.out.println(map.replace(1, "s1", "s2"));   //prints: true
        System.out.println(map);                    //prints: {1=s2, 2=s2, 3=s3}

    }

    private static void removeByKey(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, null);
        map.put(2, "s2");
        map.put(3, "s3");

        Set<Integer> keys = map.keySet();

        System.out.println(keys.remove(2));   //prints: true
        System.out.println(map);                 //prints: {1=null, 3=s3}

        System.out.println(keys.remove(4));   //prints: false
        System.out.println(map);                 //prints: {1=null, 3=s3}

    }

    private static void remove(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, null);
        map.put(2, "s2");
        map.put(3, "s3");

        System.out.println(map.remove(2));   //prints: s2
        System.out.println(map);                  //prints: {1=null, 3=s3}

        System.out.println(map.remove(4));   //prints: null
        System.out.println(map);                  //prints: {1=null, 3=s3}

        System.out.println(map.remove(3, "s4"));  //prints: false
        System.out.println(map);                  //prints: {1=null, 3=s3}

        System.out.println(map.remove(3, "s3"));  //prints: true
        System.out.println(map);                  //prints: {1=null}

    }

    private static void retrieve(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, null);
        map.put(2, "s2");
        map.put(3, "s3");

        System.out.println(map.get(2));   //prints: s2
        System.out.println(map.getOrDefault(2, "s4"));  //prints: s2
        System.out.println(map.getOrDefault(4, "s4"));  //prints: s4

        Map.Entry<Integer, String> entry = Map.entry(42, "s42");
        System.out.println(entry);   //prints: 42=s42

        Map<Integer, String> entries = Map.ofEntries(entry, Map.entry(43, "s43"));
        System.out.println(entries);  //prints: {42=s42, 43=s43}

    }

    private static void addMap(){
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, null);
        map1.put(2, "s2");
        map1.put(3, "s3");

        Map<Integer, String> map2 = new HashMap<>();
        map2.put(1, "s1");
        map2.put(2, null);
        map2.put(4, "s4");

        map1.putAll(map2);
        System.out.println(map1); //prints: {1=s1, 2=null, 3=s3, 4=s4}
    }

    private static void iterate(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, null);
        map.put(2, "s2");
        map.put(3, "s3");

        for(Integer key: map.keySet()){
            System.out.println("key=" + key + ", value=" + map.get(key));
        }

        map.keySet().stream()
                .forEach(k->System.out.println("key=" + k + ", value=" + map.get(k)));

        for(String value: map.values()){
            System.out.println("value=" + value);
        }

        map.values().stream().forEach(System.out::println);

        map.forEach((k,v) -> System.out.println("key=" + k + ", value=" + v));

        map.entrySet().forEach(e -> System.out.println("key=" + e.getKey() + ", value=" + e.getValue()));

    }

    private static void add(){

        Map<Integer, String> map = new HashMap<>();
        System.out.println(map.put(1, null));  //prints: null
        System.out.println(map.put(1, "s1"));  //prints: null
        System.out.println(map.put(2, "s1"));  //prints: null
        System.out.println(map.put(2, "s2"));  //prints: s1
        System.out.println(map.put(3, "s3"));  //prints: null
        System.out.println(map);               //prints: {1=s1, 2=s2, 3=s3}

        System.out.println(map.putIfAbsent(1, "s4"));  //prints: s1
        System.out.println(map);               //prints: {1=s1, 2=s2, 3=s3}

        System.out.println(map.put(1, null));  //prints: s1
        System.out.println(map);               //prints: {1=null, 2=s2, 3=s3}

        System.out.println(map.putIfAbsent(1, "s4"));  //prints: null
        System.out.println(map);               //prints: {1=s4, 2=s2, 3=s3}

        System.out.println(map.putIfAbsent(4, "s4"));  //prints: null
        System.out.println(map);               //prints: {1=s4, 2=s2, 3=s3, 4=s4}

    }
}

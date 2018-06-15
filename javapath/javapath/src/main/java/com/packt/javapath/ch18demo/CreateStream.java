package com.packt.javapath.ch18demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreateStream {
    public static void main(String... args) {

        of1();
        iterate();
        concat();
        generate1();
        generate2();
        of2();
        builder1();
        builder2();
        builder3();
        collection();
        array();
        random();
    }

    private static void random(){
        System.out.println();
        new Random().ints(5, 8).limit(5).forEach(System.out::print);    //prints: 56757

    }

    private static void array(){
        System.out.println();
        int[] arr = {1, 2, 3, 4, 5};
        Arrays.stream(arr, 2, 4).forEach(System.out::print);    //prints: 34

    }

    private static void collection(){
        System.out.println();
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        list.stream().forEach(System.out::print);    //prints: 12345
    }

    private static void builder3(){
        System.out.println();
        List<String> list = List.of("cat", " dog", " bear");
        buildStream(list).forEach(System.out::print);        //prints: cat bear
    }

    private static Stream<String> buildStream(List<String> values){
        System.out.println();
        Stream.Builder<String> builder = Stream.builder();
        for(String s: values){
            if(s.contains("a")){
                builder.accept(s);
            }
        }
        return builder.build();
    }

    private static void builder2(){
        System.out.println();
        Stream.Builder<String> builder = Stream.builder();
        List.of("1", "2", "3").stream().forEach(builder);
        builder.build().forEach(System.out::print);        //prints: 123
    }


    private static void builder1(){
        System.out.println();
        Stream.<String>builder().add("cat").add(" dog").add(" bear")
                .build().forEach(System.out::print);  //prints: cat dog bear

    }


    private static void of2(){
        System.out.println();
        Stream.of("1 ", 2).forEach(System.out::print);      //prints: 1 2
        //Stream<String> stringStream = Stream.of("1 ", 2); //compilation error

        System.out.println();

        String[] strings = {"1 ", "2"};
        Stream.of(strings).forEach(System.out::print);      //prints: 1 2

        System.out.println();

        Stream<Integer> stream1 = Stream.of(1, 2);
        Stream<Integer> stream2 = Stream.of(2, 3);
        Stream<Integer> stream3 = Stream.of(3, 4);
        Stream<Integer> stream4 = Stream.of(4, 5);

        Stream.of(stream1, stream2, stream3, stream4)
                .forEach(System.out::print);      //prints: java.util.stream.ReferencePipeline$Head@58ceff1j

        System.out.println();
        Stream.of(stream1, stream2, stream3, stream4)
                .flatMap(e -> e).forEach(System.out::print);      //prints: 12233445
    }

    private static int[] arr = {1,2,3,4,5};
    private static int i = 0;
    private static int getX(){
        return arr[i++];
    }

    private static void generate2(){
        System.out.println();
        Supplier<Integer> supplier = CreateStream::getX;

        Stream.generate(supplier).limit(CreateStream.arr.length)
                .forEach(System.out::print);        //prints: 12345

    }

    private static void generate1(){
        System.out.println();
        Stream.generate(() -> 1).limit(5)
                .forEach(System.out::print);            //prints: 11111
        System.out.println();
        Stream.generate(() -> new Random().nextDouble()).limit(5)
                .forEach(System.out::println);          //prints: 0.38575117472619247
                                                        //        0.5055765386778835
                                                        //        0.6528038976983277
                                                        //        0.4422354489467244
                                                        //        0.06770955839148762
    }

    private static void concat(){
        System.out.println();
        Stream<Integer> stream1 = List.of(1, 2).stream();
        Stream<Integer> stream2 = List.of(2, 3).stream();

        Stream.concat(stream1, stream2)
                .forEach(System.out::print);        //prints: 1223
    }

    private static void iterate(){
        System.out.println();
        Stream.iterate(1, i -> ++i).limit(9)
                .forEach(System.out::print);        //prints: 123456789
        System.out.println();
        Stream.iterate(1, i -> i < 10, i -> ++i)
                .forEach(System.out::print);        //prints: 123456789
    }

    private static void of1(){
        System.out.println();
        Stream.empty().forEach(System.out::println);        //prints nothing
        Stream.of(1).forEach(System.out::println);          //prints: 1

        List<String> list = List.of("1 ", "2");
        //printList1(null);                                 //NullPointerException
        printList1(list);                                   //prints: 1 2

        printList2(null);                                   //prints nothing
        System.out.println();
        printList2(list);                                   //prints: [1 , 2]

        System.out.println();
        Stream.ofNullable(list)
                .flatMap(Collection::stream)
                .forEach(System.out::print);                //prints: 1 2

    }

    private static void printList1(List<String> list){
        System.out.println();
        list.stream().forEach(System.out::print);;
    }
    private static void printList2(List<String> list){
        System.out.println();
        Stream.ofNullable(list).forEach(System.out::print);
    }

}

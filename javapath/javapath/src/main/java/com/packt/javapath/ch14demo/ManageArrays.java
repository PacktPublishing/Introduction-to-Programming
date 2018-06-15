package com.packt.javapath.ch14demo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ManageArrays {

    public static void main(String... args){

        initialize();
        equal();
    }

    private static void equal(){
        Integer[] as1 = {1,2,3};
        Integer[] as2 = {1,2,3};
        System.out.println(as1.equals(as2));               //prints: false
        System.out.println(Arrays.equals(as1, as2));       //prints: true
        System.out.println(Arrays.deepEquals(as1, as2));   //prints: true

        Integer[][] aas1 = {{1,2,3},{4,5,6}};
        Integer[][] aas2 = {{1,2,3},{4,5,6}};
        System.out.println(Arrays.equals(aas1, aas2));       //prints: false
        System.out.println(Arrays.deepEquals(aas1, aas2));   //prints: true

        Integer[][][] aaas1 = {{{1,2,3},{4,5,6}}, {{7,8,9},{10,11,12}}};
        Integer[][][] aaas2 = {{{1,2,3},{4,5,6}}, {{7,8,9},{10,11,12}}};
        System.out.println(Arrays.deepEquals(aaas1, aaas2)); //prints: true

    }

    private static void initialize() {

        int[] ints = new int[10];
        System.out.println(ints[0]);     //prints: 0

        Integer[] intW = new Integer[10];
        System.out.println(intW[0]);     //prints: null

        boolean[] bs = new boolean[10];
        System.out.println(bs[0]);       //prints: false

        Boolean[] bW = new Boolean[10];
        System.out.println(bW[0]);       //prints: 0

        String[] strings = new String[10];
        System.out.println(strings[0]);  //prints: null

        A[] as = new A[10];
        System.out.println(as[0]);       //prints: null
        System.out.println(as.length);   //prints: 10

        //A[][] as2 = new A[][10];       //compilation error
        A[][] as2 = new A[10][];
        System.out.println(as2.length);    //prints: 10
        System.out.println(as2[0]);        //prints: null
        //System.out.println(as2[0].length); ///NullPointerException
        //System.out.println(as2[0][0]);   //NullPointerException

        as2 = new A[2][3];
        System.out.println(as2[0]);     //prints: [Lcom.packt.javapath.ch14demo.ManageArrays$A;@282ba1e
        System.out.println(as2[0].length); ///prints: 3
        System.out.println(as2[0][0]);     //prints: null

        System.out.println(Arrays.toString(as2));   //[[Lcom.packt.javapath.ch14demo.ManageArrays$A;@282ba1e, [Lcom.packt.javapath.ch14demo.ManageArrays$A;@13b6d03]
        System.out.println(Arrays.toString(as2[0])); //[null, null, null]
        System.out.println(Arrays.deepToString(as2)); //[[null, null, null], [null, null, null]]


        List<Integer> list = List.of(0, 1, 2, 3, 4, 5);
        Integer[] arr1 = list.toArray(new Integer[list.size()]);
        System.out.println(Arrays.toString(arr1)); //prints: [0, 1, 2, 3, 4, 5]

        String[] arr2 = new String[3];
        Arrays.fill(arr2, "s");
        System.out.println(Arrays.toString(arr2));  //prints: [s, s, s]

        String[] arr3 = new String[5];
        Arrays.fill(arr3, 2, 3, "s");
        System.out.println(Arrays.toString(arr3)); //prints: [null, null, s, null, null]

        String[] arr4 = {"s0", "s1", };
        String[] arr4Copy = Arrays.copyOf(arr4,5);
        System.out.println(Arrays.toString(arr4Copy)); //prints: [s0, s1, null, null, null]

        String[] arr5 = {"s0", "s1", "s2", "s3", "s4" };
        String[] arr5Copy = Arrays.copyOfRange(arr5,1, 3);
        System.out.println(Arrays.toString(arr5Copy));  //prints: [s1, s2]

        Integer[] arr6 = {0, 1, 2, 3, 4 };
        Object[] arr6Copy = Arrays.copyOfRange(arr6,1, 3, Object[].class);
        System.out.println(Arrays.toString(arr6Copy));  //prints: [1, 2]

        String[] arr7 = Stream.of("s0", "s1", "s2").toArray(String[]::new);
        System.out.println(Arrays.toString(arr7));     //prints: [s0, s1, s2]

    }

    static class A{
        @Override
        public String toString() { return "A"; }
    }
    static class B extends A{}


    private static A[] AS_STATIC;
    static {
        AS_STATIC = new A[2];
        for(int i = 0; i< AS_STATIC.length; i++){
            AS_STATIC[i] = new A();
        }
        AS_STATIC[0] = new A();
        AS_STATIC[1] = new A();
    }
    private A[] as;

    public ManageArrays(){
        as = new A[2];
        for(int i = 0; i< as.length; i++){
            as[i] = new A();
        }
        as[0] = new A();
        as[1] = new A();
    }



}

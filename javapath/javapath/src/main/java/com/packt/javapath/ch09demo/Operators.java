package com.packt.javapath.ch09demo;

public class Operators {
    public static void main(String[] args) {

        arithmeticOperators();
        incrementOperators();
        equalityOperators();
        relationalOperators();
        logicalOperators();
        conditionalOperators();
        assignmentOperators();
        newOperator();
        new Operators().instanceofOperator();
        new Operators().polymorphismBenefits();
        fieldMethodAccessOperator();
        new Operators().castOperator();
        operatorsPrecedence();
    }

    private static void operatorsPrecedence(){
        int x = 0;
        int y = x++ + x++;
        System.out.println(y);   //prints: 1
        System.out.println(x);   //prints: 2

        int n = 0;
        int m = 5*n++;
        System.out.println(m);   //prints: 0
        System.out.println(n);   //prints: 1

        int k = 1;
        int[] arr = {88, 5, 42};
        System.out.println(arr[k++]);  //prints: 5
        System.out.println(k);         //prints: 2
        System.out.println(arr[k++]);  //prints: 42
        System.out.println(k);         //prints: 3

        System.out.println((double)11/3);  //prints: 3.6666666666666665
        double d = (double)11/3;
        System.out.println(d);

        int p1 = 10, p2 = 1;
        int q = (p1 += 3)  +  (p2 += 3);
        System.out.println(q);         //prints: 17
        System.out.println(p1);        //prints: 13
        System.out.println(p2);        //prints: 4

        p1 = 10;
        p2 = 1;
        q = p1 += 3  +  (p2 += 3);
        System.out.println(q);         //prints: 17
        System.out.println(p1);        //prints: 17
        System.out.println(p2);        //prints: 4

        int a = 0, b = 0;
        int c = a++ + (a * ++b);
        System.out.println(c);         //prints: 1
    }

    interface I1{}
    interface I2{}
    interface I3{}
    class A implements I1, I2 {}
    class B extends A implements I3{}
    class C extends B {}
    class D {}
    private void castOperator(){
        System.out.println();
        C c = new C();
        A a = (A)c;
        I1 i1 = (I1)c;
        I2 i2 = (I2)c;
        I3 i3 = (I3)c;
        c = (C)a;
        D d = new D();
        //a = (A)d;      //compilation error
        //i1 = (I1)d;    //run-time error

        Integer integer1 = 3;
        System.out.println(integer1);  //prints: 3
        Integer integer2 = Integer.valueOf(4);
        int i = integer2;
        System.out.println(i);         //prints: 4
    }
    private interface InterfaceM {
        String INTERFACE_FIELD = "interface field";
        static void staticMethod1(){
            System.out.println("interface static method 1");
        }
        static void staticMethod2(){
            System.out.println("interface static method 2");
        }
        default void method1(){
            System.out.println("interface default method 1");
        }
        default void method2(){
            System.out.println("interface default method 2");
        }
        void method3();
    }
    private static class ClassM implements InterfaceM {
        public static String CLASS_STATIC_FIELD = "class static field";
        public static void staticMethod2(){
            System.out.println("class static method 2");
        }
        public static void staticMethod3(){
            System.out.println("class static method 3");
        }

        public String instanceField = "instance field";
        public void method2(){
            System.out.println("class instance method 2");
        }
        public void method3(){
            System.out.println("class instance method 3");
        }
    }
    private static void fieldMethodAccessOperator(){

        System.out.println(InterfaceM.INTERFACE_FIELD);    //1: interface field
        InterfaceM.staticMethod1();                //2: interface static method
        InterfaceM.staticMethod2();                //3: interface static method
        //InterfaceM.method1();                          //4: compilation error
        //InterfaceM.method2();                          //5: compilation error
        //InterfaceM.method3();                          //6: compilation error

        System.out.println(ClassM.INTERFACE_FIELD);       //7: interface field
        //ClassM.staticMethod1();                       //8: compilation error
        ClassM.staticMethod2();                     //9: class static method 2
        ClassM.staticMethod3();                    //10: class static method 3

        ClassM classM = new ClassM();
        System.out.println(ClassM.CLASS_STATIC_FIELD); //11: class static field
        System.out.println(classM.CLASS_STATIC_FIELD); //12: class static field
        //System.out.println(ClassM.instanceField);     //13: compilation error
        System.out.println(classM.instanceField);          //14: instance field
        //classM.staticMethod1();                       //15: compilation error
        classM.staticMethod2();                    //16: class static method  2
        classM.staticMethod3();                     //17: class static method 3
        classM.method1();                      //18: interface default method 1
        classM.method2();                         //19: class instance method 2
        classM.method3();                         //20: class instance method 3
    }

    private static void newOperator(){
        int[] arrInt = new int[42];
        //arrInt[43] = 22;
        System.out.println(arrInt[2]);      //prints: 0
        System.out.println(arrInt.length);  //prints: 42
        int[] arrInit = {1,2,3,4};
        System.out.println(arrInit[2]);      //prints: 3
        System.out.println(arrInit.length);  //prints: 4
    }

    private static void assignmentOperators(){
        int x = 1;
        x += 2;
        System.out.println(x);    //prints: 3
        x -= 1;
        System.out.println(x);    //prints: 2
        x *= 2;
        System.out.println(x);    //prints: 4
        x /= 2;
        System.out.println(x);    //prints: 2
        x %= 2;
        System.out.println(x);    //prints: 0

        x = 11;
        double y = x;
        y /= 3;
        System.out.println(y);        //prints: 3.6666666666666665
        x = (int)y;
        System.out.println(x);        //prints: 3
        double d = Math.round(y);     //prints: 4.0
        System.out.println(d);
        x = (int) d;
        System.out.println(x);        //prints: 4
    }

    private static void relationalOperators() {
        int i1 = 1;
        int i2 = 2;
        int i3 = 1;
        System.out.println(i1 > i2);  //prints: false
        System.out.println(i1 >= i2); //prints: false
        System.out.println(i1 >= i3); //prints: true
        System.out.println(i1 < i2);  //prints: true
        System.out.println(i1 <= i2); //prints: true
        System.out.println(i1 <= i3); //prints: true

        System.out.println('a' >= 'b'); //prints: false
        System.out.println('a' <= 'b'); //prints: true

        double d1 = 1/3;
        double d2 = 0.34;
        double d3 = 0.33;
        System.out.println(d1 < d2);   //prints: true
        System.out.println(d1 >= d3);  //prints: false
        System.out.println(d1);        //prints: 0.0
        double d4 = 1/3d;
        System.out.println(d4);        //prints: 0.3333333333333333
        System.out.println(d4 >= d3);  //prints: true
    }

    private static class SomeClass {}

    private static void equalityOperators() {
        char a = 'a';
        char b = 'b';
        char c = 'a';
        System.out.println(a == b);  //prints: false
        System.out.println(a != b);  //prints: true
        System.out.println(a == c);  //prints: true
        System.out.println(a != c);  //prints: false

        System.out.println((a + 1) == b); //prints: true
        System.out.println(b - a);        //prints: 1
        System.out.println((int)a);       //prints: 97
        System.out.println((int)b);       //prints: 98

        int i1 = 1;
        int i2 = 2;
        int i3 = 1;
        System.out.println(i1 == i2);  //prints: false
        System.out.println(i1 != i2);  //prints: true
        System.out.println(i1 == i3);  //prints: true
        System.out.println(i1 != i3);  //prints: false

        boolean b1 = true;
        boolean b2 = false;
        boolean b3 = true;
        System.out.println(b1 == b2);  //prints: false
        System.out.println(b1 != b2);  //prints: true
        System.out.println(b1 == b3);  //prints: true
        System.out.println(b1 != b3);  //prints: false

        double d1 = 0.42;
        double d2 = 0.43;
        double d3 = 0.42;
        System.out.println(d1 == d2);  //prints: false
        System.out.println(d1 != d2);  //prints: true
        System.out.println(d1 == d3);  //prints: true
        System.out.println(d1 != d3);  //prints: false

        SomeClass c1 = new SomeClass();
        SomeClass c2 = new SomeClass();
        SomeClass c3 = c1;
        System.out.println(c1 == c2);  //prints: false
        System.out.println(c1 != c2);  //prints: true
        System.out.println(c1 == c3);  //prints: true
        System.out.println(c1 != c3);  //prints: false
        System.out.println(new SomeClass() == new SomeClass());  //prints: false

        String s1 = "s1";
        String s2 = "s2";
        String s3 = s1;
        System.out.println(s1 == s2);  //prints: false
        System.out.println(s1 != s2);  //prints: true
        System.out.println(s1 == s3);  //prints: true
        System.out.println(s1 != s3);  //prints: false

        System.out.println("s1" == "s1"); //prints: true!
        System.out.println("s1" != "s1"); //prints: false!

    }

    private class ClassBase {
        void method(){
            System.out.println("ClassBase.method() is called");
        }
    }
    private class ClassY extends ClassBase {
        void method(){
            System.out.println("ClassY.method() is called");
        }
    }
    private class ClassZ extends ClassBase {
        void method(){
            System.out.println("ClassZ.method() is called");
        }
    }

    private void polymorphismBenefits() {
        //doSomething1(new ClassY());
        System.out.println();

        doSomething2(new ClassY());
    }

    private void doSomething1(ClassBase object) {
        if(object instanceof ClassY){
            ((ClassY)object).method();
        } else if(object instanceof ClassZ){
            ((ClassZ)object).method();
        }
    }

    private void doSomething2(ClassBase object) {
        object.method();
    }

    private interface InterfaceA{}
    private class ClassA implements InterfaceA {}
    private class ClassB extends ClassA {}

    private class ClassX implements InterfaceA {}

    //instanceof
    private void instanceofOperator() {
        ClassA classA = new ClassA();
        ClassB classB = new ClassB();
        ClassX classX = new ClassX();
        int[] arrI = {1,2,3};
        ClassA[] arrA = {new ClassA(), new ClassA()};

        System.out.println(classA instanceof Object); //prints: true
        System.out.println(arrI instanceof Object);   //prints: true
        System.out.println(arrA instanceof Object);   //prints: true
        //System.out.println(arrA instanceof ClassA);   //compilation error

        System.out.println(classA instanceof InterfaceA); //prints: true
        System.out.println(classB instanceof InterfaceA); //prints: true
        System.out.println(classX instanceof InterfaceA); //prints: true

        InterfaceA intA = (InterfaceA)classA;
        intA = (InterfaceA)classX;

        System.out.println(classA instanceof ClassA); //prints: true
        System.out.println(classB instanceof ClassA); //prints: true
        System.out.println(classA instanceof ClassB); //prints: false
        //System.out.println(classX instanceof ClassA); //compilation error

        System.out.println(null instanceof ClassA);   //prints: false
        //System.out.println(classA instanceof null);   //compilation error
        System.out.println(classA == null);           //prints: false
        System.out.println(classA != null);           //prints: true

        char c1 = 'a';
        char c2 = '$';
        System.out.println(c1+c2);
        System.out.println(c1/c2);
        System.out.println((float)c1/c2);
    }

    //&&  ||  ?: (ternary)
    private static void conditionalOperators() {
        boolean x = false;
        boolean y = !x;
        System.out.println(y && x); //prints: false
        System.out.println(y || x); //prints: true
        boolean z = true;
        System.out.println(y && z); //prints: true
        System.out.println(y || z); //prints: true

        int i = 1, j = 3, k = 10;
        System.out.println(i > j & i++ < k);  //prints: false
        System.out.println("i=" + i);         //prints: i=2
        System.out.println(i > j && i++ < k); //prints: false
        System.out.println("i=" + i);         //prints: i=2

        int n = 1, m = 2;
        System.out.println(n > m ? "n > m" : "n <= m"); //prints: n <= m
        System.out.println(n > m ? true : false);       //prints: false
        int max = n > m ? n : m;
        System.out.println(max);                        //prints: 2
    }

    //!  &  |
    private static void logicalOperators() {
        boolean x = false;
        System.out.println(!x);  //prints: true
        System.out.println(!!x); //prints: false
        boolean y = !x;
        System.out.println(y & x); //prints: false
        System.out.println(y | x); //prints: true
        boolean z = true;
        System.out.println(y & z); //prints: true
        System.out.println(y | z); //prints: true
    }

    //++  --
    private static void incrementOperators() {
        int i = 2;
        System.out.println(++i);      //prints: 3
        System.out.println("i=" + i); //prints: i=3
        System.out.println(--i);      //prints: 2
        System.out.println("i=" + i); //prints: i=2

        System.out.println(i++);      //prints: 2
        System.out.println("i=" + i); //prints: i=3
        System.out.println(i--);      //prints: 3
        System.out.println("i=" + i); //prints: i=2

        int x = 0;
        ++x;
        System.out.println(x);   //prints: 1
        x = 0;
        x++;
        System.out.println(x);   //prints: 1

        int y = 0;
        int z = ++y;
        System.out.println(+z);   //prints: 1
        y = 0;
        z = y++;
        System.out.println(z);   //prints: 0

        int[] arr = {81,15,42};
        int j = 0;
        System.out.println(arr[j++]);  //prints: 81
        System.out.println(arr[j++]);  //prints: 15
        System.out.println(arr[j++]);  //prints: 42

        j = -1;
        System.out.println(arr[++j]);  //prints: 1
        System.out.println(arr[++j]);  //prints: 2
        System.out.println(arr[++j]);  //prints: 3
    }

    //+ - * / %
    private static void arithmeticOperators() {
        int i = 2;   //unary "+" is assumed by default
        int x = -i;  //unary "-" applied to positive number makes it negative
        System.out.println(x);   //prints: -2

        int y = -x;  //unary "-" applied to negative number makes it positive
        System.out.println(y);   //prints: 2

        int z = x + y;  //binary "+" means "add"
        System.out.println(z);   //prints: 0

        //In case of String, the binary "+" means "concatenate"
        String s1 = "Nick";
        String s2 = "Samoylov";
        System.out.println(s1 + " " + s2);  //prints: Nick Samoylov
        String s3 = s1 + " " + s2;
        System.out.println(s3);  //prints: Nick Samoylov

        z = x - y;  //binary "-" means "subtract"
        System.out.println(z);  //prints: -4
        System.out.println(y - x);  //prints: 4

        z = x * y;
        System.out.println(z);  //prints: -4

        z = x / y;
        System.out.println(z);  //prints: -1

        z = x * y;
        System.out.println(z % 3);  //prints: -1
        System.out.println(z % 2);  //prints: 0
        System.out.println(z % 4);  //prints: 0

        int i1 = 11;
        int i2 = 3;
        System.out.println(i1 / i2);         //prints: 3
        System.out.println(i1 % i2);         //prints: 2
        double d1 = 11;
        System.out.println(d1/i2);           //prints: 3.6666666666666665
        System.out.println((float)i1 / i2);  //prints: 3.6666667
        System.out.println(i1 / (double)i2); //prints: 3.6666666666666665
        System.out.println(i1 * 1.0 / i2);   //prints: 3.6666666666666665
        System.out.println(i1 * 1f / i2);    //prints: 3.6666667
        System.out.println(i1 * 1d / i2);    //prints: 3.6666666666666665

        float r = (float)i1 / i2;
        System.out.println(r);                 //prints: 3.6666667
        float f = Math.round(r * 100f) / 100f;
        System.out.println(f);                 //prints: 3.67
        int i3 = (int)f;
        System.out.println(i3);                //prints: 3
    }

}

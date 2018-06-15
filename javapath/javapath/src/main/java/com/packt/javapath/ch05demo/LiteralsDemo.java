package com.packt.javapath.ch05demo;

public class LiteralsDemo {
    public static void main(String[] args) {

        System.out.println("\nboolean literal true: " + true);
        System.out.println("boolean literal false: " + false);

        System.out.println("\nchar literal 'a': " + 'a');
        System.out.println("char literal '%': " + '%');
        System.out.println("char literal '\u03a9': " + '\u03a9'); //Greek Capital Letter Omega
        System.out.println("char literal '™': " + '™');  //Trade mark sign


        System.out.println("\nSome printable characters:");
        for(int i = 160; i <= 200; i++){
            printableChar(i);
        }

        System.out.println("\nSome non-printable characters:");
        for(int i = 0; i <= 32; i++){
            nonPrintableChar(i);
        }

        System.out.println("\nThe line breaks \nhere");
        System.out.println("The tab is\there");
        System.out.println("\"");
        System.out.println('\'');
        System.out.println('\\');

        System.out.println("\nfloat literal 123.456f: " + 123.456f);
        System.out.println("double literal 123.456d: " + 123.456d);

        float f1 = 123.456f;
        System.out.println("\nfloat literal 123.456f: " + f1);
        System.out.println("float literal 123.456f in decimal format: " + String.format("%f", f1));
        System.out.println("float literal 123.456f in scientific format: " + String.format("%e", f1));

        double d1 = 123.456d;
        System.out.println("\ndouble literal 123.456d: " + d1);
        System.out.println("double literal 123.456d in decimal format: " + String.format("%f", d1));
        System.out.println("double literal 123.456d in scientific format: " + String.format("%e", d1));


        float f2 = 1.234560e+02f;
        System.out.println("\nfloat literal 1.234560e+02f: " + f2);
        System.out.println("float literal 1.234560e+02f in decimal format: " + String.format("%f", f2));
        System.out.println("float literal 1.234560e+02f in scientific format: " + String.format("%e", f2));

        double d2 = 1.234560e+02d;
        System.out.println("\ndouble literal 1.234560e+02d: " + d2);
        System.out.println("double literal 1.234560e+02d in decimal format: " + String.format("%f", d2));
        System.out.println("double literal 1.234560e+02d in scientific format: " + String.format("%e", d2));

        System.out.println("\nfloat literal 1.234560e+02f: " + 1.234560e+02f);
        System.out.println("double literal 1.234560e+02d: " + 1.234560e+02d);

        convert("12", 10);
        System.out.println("\nPrint literal 12:");
        System.out.println("- bin 0b1100: "+ 0b1100);
        System.out.println("- oct    014: "+ 014);
        System.out.println("- dec     12: "+ 12);
        System.out.println("- hex    0xc: "+ 0xc);

        convert("-12", 10);

        System.out.println("\nPrint literal -12:");
        System.out.println("- bin 0b1100: "+ -0b1100);
        System.out.println("- oct    014: "+ -014);
        System.out.println("- dec     12: "+ -12);
        System.out.println("- hex    0xc: "+ -0xc);


        int spedOfLightMilesSec = 299_792_458;
        float meanRadiusOfEarthMiles = 3_958.8f;
        long creditCardNumber = 1234_5678_9012_3456L;

        long anotherCreditCardNumber = 9876____5678_____9012____1234L;
        System.out.println("\n"+anotherCreditCardNumber);
    }

    private static void printableChar(int c){
        System.out.println("char literal '" +
                String.format("\\u%04x", c) +
                "': " + Character.valueOf((char)c) +
                ", code point=" + c + ", name: " +
                Character.getName(c));
    }

    private static void nonPrintableChar(int c){
        System.out.println("char literal '" +
                String.format("\\u%04x", c) +
                "': code point=" + c + ", name: " +
                Character.getName(c));
    }

    public static void convert(String n, int base){
        System.out.println("\nConvert convert number " + n + " (base " + base + "):");

        int i = Integer.parseInt(n, base);

        System.out.println("- bin: " + Integer.toString(i, 2));
        System.out.println("- bin: " + Integer.toBinaryString(i));

        System.out.println("- oct: " + Integer.toString(i, 8));
        System.out.println("- oct: " + Integer.toOctalString(i));

        System.out.println("- dec: " + Integer.toString(i, 10));
        System.out.println("- dec: " + Integer.toString(i));

        System.out.println("- hex: " + Integer.toString(i, 16));
        System.out.println("- hex: " + Integer.toHexString(i));
    }

}

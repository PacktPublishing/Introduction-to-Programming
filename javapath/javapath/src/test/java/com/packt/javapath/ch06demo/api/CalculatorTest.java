package com.packt.javapath.ch06demo.api;

import com.packt.javapath.ch06demo.Utils;
import com.packt.javapath.ch06demo.api.Calculator.WhichImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("API Calculator tests")
public class CalculatorTest {

    @Test
    @DisplayName("Happy multiplyByTwo()")
    void multiplyByTwo() {
        //Calculator calculator = CalculatorFactory.create();

        WhichImpl whichImpl = Utils.getWhichImplValueFromConfig(Calculator.CONF_NAME, Calculator.CONF_WHICH_IMPL);
        System.out.println("\n" + Calculator.CONF_WHICH_IMPL + "=" + whichImpl);

        Calculator calculator = Calculator.createInstance();
        int i = 2;
        int result = calculator.multiplyByTwo(i);
        assertEquals(4, result);

        double d = 2.12345678;
        String mString = "3.12345678";
        String aString = "3.12";
        String s = Calculator.addOneAndConvertToString(d);
        if(whichImpl.equals(Calculator.WhichImpl.multiplies)){
            assertEquals(mString, s);
        } else {
            assertNotEquals(mString, s);
        }
        if(whichImpl.equals(Calculator.WhichImpl.adds)){
            assertEquals(aString, s);
        } else {
            assertNotEquals(aString, s);
        }
    }
}

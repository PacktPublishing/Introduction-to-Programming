package com.packt.javapath.ch04demo.math;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("My first test case")
class SimpleMathTest {

    @Test
    @DisplayName("Happy path")
    void multiplyByTwo(){
        SimpleMath simpleMath = new SimpleMath();
        int i = 2;
        int result = simpleMath.multiplyByTwo(i);
        assertEquals(4, result);
    }

}

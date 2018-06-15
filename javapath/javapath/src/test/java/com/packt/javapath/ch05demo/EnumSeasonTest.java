package com.packt.javapath.ch05demo;

import com.packt.javapath.ch05demo.ReferenceTypeDemo.Season3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Enum Season tests")
public class EnumSeasonTest {

    @Test
    @DisplayName("Test Spring getters")
    void multiplyByTwo(){
        assertEquals("Spring", Season3.SPRING.toString());
        assertEquals("warmer than winter", Season3.SPRING.getFeel());
        assertEquals(60, Season3.SPRING.getAverageTemperature());
    }
}

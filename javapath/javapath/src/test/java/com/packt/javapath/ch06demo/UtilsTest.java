package com.packt.javapath.ch06demo;

import com.packt.javapath.ch06demo.api.Calculator.WhichImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Utils tests")
public class UtilsTest {

    @Test
    @DisplayName("Test reading value from config file by key")
    void getStringValueFromConfig(){
        String configFileName = "utilstest.conf";

        String value = Utils.getStringValueFromConfig(configFileName, "unknown");
        assertEquals("unknown", value);

        value = Utils.getStringValueFromConfig(configFileName, "some value");
        assertEquals("unknown", value);
    }

    @Test
    @DisplayName("Test matching config value to enum WhichImpl")
    void getWhichImpValueFromConfig(){
        String confifFileName = "utilstest.conf";
        for(int i = 1; i <= WhichImpl.values().length; i++){
            String key = String.valueOf(i);
            WhichImpl whichImpl = Utils.getWhichImplValueFromConfig(confifFileName, key);
            System.out.println(key + "=" + whichImpl);
        }
        try {
            WhichImpl whichImpl = Utils.getWhichImplValueFromConfig(confifFileName, "unknown");
            fail("Should not get here! whichImpl = " + whichImpl);
        } catch (RuntimeException ex){
            assertEquals("Houston, we have a problem. Unknown key which.impl value unknown is in config.", ex.getMessage());
        }
        try {
            WhichImpl whichImpl = Utils.getWhichImplValueFromConfig(confifFileName, "some value");
            fail("Should not get here! whichImpl = " + whichImpl);
        } catch (RuntimeException ex){
            assertEquals("Houston, we have a problem. Unknown key which.impl value unknown is in config.", ex.getMessage());
        }
    }
}


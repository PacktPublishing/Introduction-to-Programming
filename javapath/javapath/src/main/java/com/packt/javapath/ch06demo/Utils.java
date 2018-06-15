package com.packt.javapath.ch06demo;

import com.packt.javapath.ch06demo.api.Calculator;
import com.packt.javapath.ch06demo.api.Calculator.WhichImpl;
/*
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Utils {
    private static JsonObject config = null;
    public static String getStringValueFromConfig(String configFileName, String key){
        if(config == null){
            ClassLoader classLoader = Utils.class.getClassLoader();
            File file = new File(classLoader.getResource(configFileName).getFile());
            try(FileInputStream fis = new FileInputStream(file)){
                JsonReader reader = Json.createReader(fis);
                config = reader.readObject();
            } catch (IOException ex){
                ex.printStackTrace();
                return "Problem reading config file.";
            }
        }
        return config.get(key) == null ? "unknown" : config.getString(key);
    }
/*
    private static JSONObject config = null;
    public static String getStringValueFromConfig(String configFileName, String key){
        if(config == null){
            ClassLoader classLoader = Utils.class.getClassLoader();
            File file = new File(classLoader.getResource(configFileName).getFile());
            try(FileReader fr = new FileReader(file)){
                JSONParser parser = new JSONParser();
                config = (JSONObject) parser.parse(fr);
            } catch (ParseException | IOException ex){
                ex.printStackTrace();
                return "Problem reading config file.";
            }
        }
        return config.get(key) == null ? "unknown" : (String)config.get(key);
    }
*/

    public static WhichImpl getWhichImplValueFromConfig(String configFileName, String key){
        String whichImpl = Utils.getStringValueFromConfig(configFileName, key);
        try{
            return Enum.valueOf(WhichImpl.class, whichImpl);
        } catch (IllegalArgumentException ex){
            throw new RuntimeException("Houston, we have a problem. " +
                    "Unknown key " + Calculator.CONF_WHICH_IMPL +
                    " value " + whichImpl + " is in config.");
        }
    }

}

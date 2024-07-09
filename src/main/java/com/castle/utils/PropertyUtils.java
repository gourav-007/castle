package com.castle.utils;

import com.castle.constants.FCWithSingleton;
import com.castle.enums.PropertyType;
import org.codehaus.groovy.tools.shell.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public final class PropertyUtils {
    private PropertyUtils(){}

    //read the content from prop and store it in hashmap.
    //read content only once and store it in some java collections

    private static Properties properties = new Properties();
    private static Map<String,String> map = new ConcurrentHashMap<>();

    static {
        try(FileInputStream fileInputStream = new FileInputStream(FCWithSingleton.getInstance().getPropertyFilePath())) {//Closable Interface-->try-with-resources
            properties.load(fileInputStream);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }

        properties.forEach((key, value) -> map.put(String.valueOf(key), String.valueOf(value)));
    }

    public static String getValue(PropertyType key){
        return map.get(key.name().toLowerCase());
    }

}

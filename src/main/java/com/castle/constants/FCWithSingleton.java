package com.castle.constants;

import lombok.Getter;

@Getter
public class FCWithSingleton {

    private static FCWithSingleton INSTANCE=null;

    private FCWithSingleton(){};

    public static synchronized FCWithSingleton getInstance(){
        if (INSTANCE==null){
            INSTANCE=new FCWithSingleton();
        }
        return INSTANCE;
    }

    private final String requestJsonFolderPath = System.getProperty("user.dir") + "/src/test/resources/jsons/";
    private final String responseJsonFolderPath = System.getProperty("user.dir") + "/output/";
    private final String propertyFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
}

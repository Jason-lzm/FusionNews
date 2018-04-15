package com.lzm.fusionnews.utils;

import com.google.gson.Gson;

/**
 * @description Json conversion class
 */
public class JsonUtil {

    /**
     * @description convert json string to a class object
     * @param jsonData
     * @param clazz
     */
    public static final <T> T fromJson(String jsonData, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, clazz);
    }

    /**
     * @description convert a class object to json string
     * @param jsonData
     */
    public static final String toJson(Object jsonData) {
        Gson gson = new Gson();
        return gson.toJson(jsonData);
    }
    
}

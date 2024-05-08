package com.example.spring_boot_project_analytics.MainTable.Helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Convert an object to a JSON string.
     * @param object the object to convert
     * @return a JSON string representation of the object
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting object to JSON", e);
        }
    }

    /**
     * Convert a JSON string to an object of the specified type.
     * @param json the JSON string
     * @param clazz the class of the type to convert to
     * @param <T> the type of the object to return
     * @return an object of type T
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to object", e);
        }
    }
}


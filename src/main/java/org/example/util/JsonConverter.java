package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonConverter {
    public static String getConvertedToJson(Object o){
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String jsonString;
        try {
            jsonString = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return "Failed to convert object";
        }
        return jsonString;
    }
}

package com.custom.spring.sso.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonConverter {
    public final static ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(JsonConverter.class);

    public static <T> String convertToString(T object) {
        logger.info("Converting object: {} ", object);
        if (object == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;

        }
    }

    public static <T> T convertToObject(String dbData, Class<T> clazz) {
        logger.info("Converting to object:{} ", dbData);
        if (dbData == null) {
            return null;
        }
        return objectMapper.convertValue(dbData, clazz);
    }
}
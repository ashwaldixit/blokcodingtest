package com.blocksports.assignment.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Map;

@Converter(autoApply = true)
public class JsonConverter implements AttributeConverter<Map<String, ? extends Object>, String> {

    private static final long serialVersionUID = 1L;
    private static final ObjectMapper MAPPER = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(Map<String, ? extends Object> map) {


        try {
           return MAPPER.writeValueAsString(map);
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException(ex);
        }

    }

    @Override
    public Map<String, ? extends Object> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return MAPPER.readValue(dbData, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException ex) {
            return null;
        }
    }
}

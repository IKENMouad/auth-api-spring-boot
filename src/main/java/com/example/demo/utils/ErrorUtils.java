package com.example.demo.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ErrorUtils {

    public static String customErrors(List<ObjectError> errors) throws Exception {
        Map<String, String> errorsMap = new HashMap<>();
        for (ObjectError error : errors) {
            if (error instanceof FieldError) {
                FieldError errorField = (FieldError) error;
                errorsMap.put(errorField.getField(), errorField.getDefaultMessage());
            }
        }
        errorsMap.put("status", "failure");
        String json = new ObjectMapper().writeValueAsString(errorsMap);
        return json;
    }
}

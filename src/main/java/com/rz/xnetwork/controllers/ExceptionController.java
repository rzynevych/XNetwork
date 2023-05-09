package com.rz.xnetwork.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<String> handleException(Exception e) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("error", e.getClass().getName());
        map.put("message", e.getMessage());
        map.put("stackTrace", Arrays.stream(e.getStackTrace()).map(s -> s.toString()).collect(Collectors.toList()));
        map.put("cause", e.getCause().getMessage());
        BodyBuilder resp = ResponseEntity.badRequest();
        return resp.body(objectMapper.writeValueAsString(map));
    }
    
}

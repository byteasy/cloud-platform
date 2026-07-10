package com.byteasy.platform.dto;

import lombok.Data;

import java.util.Map;

@Data
public class JsRunDTO {
    private String script;
    private Map<String, Object> input;
    private Object output;
}

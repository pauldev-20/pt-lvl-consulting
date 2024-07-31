package com.jira.api_jira.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseApi<T> {
    private String message = "Success";
    private T data;
    private boolean status;
    private int code;
}
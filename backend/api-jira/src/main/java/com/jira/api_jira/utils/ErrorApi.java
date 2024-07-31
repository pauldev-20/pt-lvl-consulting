package com.jira.api_jira.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorApi {
    private boolean status;
    private int code;
    private String message;
}

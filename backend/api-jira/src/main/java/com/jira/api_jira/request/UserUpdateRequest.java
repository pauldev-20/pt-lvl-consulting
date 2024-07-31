package com.jira.api_jira.request;

import org.springframework.web.multipart.MultipartFile;

import com.jira.api_jira.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateRequest {
    private User user;
    private MultipartFile photoFile;
}

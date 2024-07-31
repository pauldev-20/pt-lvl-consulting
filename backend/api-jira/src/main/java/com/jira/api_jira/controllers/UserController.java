package com.jira.api_jira.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jira.api_jira.dto.ProjectDto;
import com.jira.api_jira.models.User;
import com.jira.api_jira.services.ProjectService;
import com.jira.api_jira.services.UserService;
import com.jira.api_jira.utils.ResponseApi;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    /**
     * This method is called for a PUT request
     * Url: localhost:8080/api/v1/users
     * Purpose: Fetch a user by id and update it
     * 
     * @return User updated
     * @throws Exception
     */
    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody User request) throws Exception {
        try {
            User user = userService.updateUser(request);
            ResponseApi<User> response = new ResponseApi<>(
                    "Success",
                    user,
                    true,
                    HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
    * This method is called for a GET request
    * Url: localhost:8080/api/v1/users/{id}/projects
    * Purpose: Fetching all projects by user_id
    * 
    * @return Project list
    * @throws Exception
    */
    @GetMapping("/{id}/projects")
    public ResponseEntity<Object> getProjects(@PathVariable("id") Integer userId) throws Exception {
        try {
            List<ProjectDto> projects = projectService.getProjects(userId);
            ResponseApi<List<ProjectDto>> response = new ResponseApi<>(
                    "Success",
                    projects,
                    true,
                    HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
}

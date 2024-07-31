package com.jira.api_jira.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jira.api_jira.dto.ProjectDto;
import com.jira.api_jira.models.Project;
import com.jira.api_jira.services.ProjectService;
import com.jira.api_jira.utils.ResponseApi;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * This method is called for a POST request
     * Url: localhost:8080/api/v1/projects
     * Purpose: Store a new Project for User
     * 
     * @return Project store
     * @throws Exception
     */
    @PostMapping
    public ResponseEntity<Object> saveProject(@RequestBody Project request) throws Exception {
        try {
            System.out.println(request);
            ProjectDto project = projectService.saveProject(request);
            ResponseApi<ProjectDto> response = new ResponseApi<>(
                    "Success",
                    project,
                    true,
                    HttpStatus.CREATED.value());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This method is called for a GET request
     * Url: localhost:8080/api/v1/projects
     * Purpose: Fetching all projects filtered by code, name, state, date_start,
     * date_end and user_id
     * 
     * @return Project list
     * @throws Exception
     */
    @GetMapping
    public ResponseEntity<Object> getProjectsAvanced(
            @RequestParam(name = "user_id") Integer userId,
            @RequestParam(name = "code", defaultValue = "all") String code,
            @RequestParam(name = "name", defaultValue = "all") String name,
            @RequestParam(name = "state", defaultValue = "all") String state,
            @RequestParam(name = "date_start", defaultValue = "all") String dateStart,
            @RequestParam(name = "date_end", defaultValue = "all") String dateEnd) throws Exception {
        try {
            List<ProjectDto> projects = projectService.getProjectsAvanced(userId, code, name, state, dateStart, dateEnd);
            ResponseApi<List<ProjectDto>> response = new ResponseApi<>(
                    "Success",
                    projects,
                    true,
                    HttpStatus.CREATED.value());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

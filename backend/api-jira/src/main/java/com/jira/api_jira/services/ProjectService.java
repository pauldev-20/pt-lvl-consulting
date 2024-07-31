package com.jira.api_jira.services;

import java.util.List;

import com.jira.api_jira.dto.ProjectDto;
import com.jira.api_jira.models.Project;

public interface ProjectService {
    ProjectDto saveProject(Project project);
    List<ProjectDto> getProjects(Integer userId);
    List<ProjectDto> getProjectsAvanced(Integer userId, String code, String name, String state, String dateStart, String dateEnd);
}
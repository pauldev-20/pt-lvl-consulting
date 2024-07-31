package com.jira.api_jira.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.api_jira.dto.ProjectDto;
import com.jira.api_jira.models.Project;
import com.jira.api_jira.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{
    
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectDto saveProject(Project project) {
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());
        Project projectSaved = projectRepository.save(project);
        return convertToDto(projectSaved);
    }

    @Override
    public List<ProjectDto> getProjects(Integer userId) {
        List<Project> projects = projectRepository.findProjectsByIdUser(userId);
        return projects.stream().map(this::convertToDto).toList();
    }

    @Override
    public List<ProjectDto> getProjectsAvanced(Integer userId, String code, String name, String state, String dateStart, String dateEnd) {
        List<Project> projects = projectRepository.findProjectsByIdUser(userId);
        Stream<Project> stream = projects.stream();
        if (!code.equals("all")) {
            stream = stream.filter(project -> project.getCode().equalsIgnoreCase(code));
        }
        if (!name.equals("all")) {
            stream = stream.filter(project -> project.getName().equalsIgnoreCase(name));
        }
        if (!state.equals("all")) {
            stream = stream.filter(project -> project.getState().toString().equalsIgnoreCase(state));
        }
        if (!dateStart.equals("all")) {
            stream = stream.filter(project -> project.getDateStart().toString().equalsIgnoreCase(dateStart));
        }
        if (!dateEnd.equals("all")) {
            stream = stream.filter(project -> project.getDateEnd().toString().equalsIgnoreCase(dateEnd));
        }
        return stream.map(this::convertToDto).toList();
    }

    public ProjectDto convertToDto(Project project) {
        return new ProjectDto(project);
    }
    
}

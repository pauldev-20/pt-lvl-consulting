package com.jira.api_jira.dto;

import java.time.LocalDate;

import com.jira.api_jira.models.Project;
import com.jira.api_jira.models.StateProject;

import lombok.Data;

@Data
public class ProjectDto {
    private Integer id;
    private String name;
    private String code;
    private String description;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Boolean share;
    private StateProject state;

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.code = project.getCode();
        this.description = project.getDescription();
        this.dateStart = project.getDateStart();
        this.dateEnd = project.getDateEnd();
        this.share = project.getShare();
        this.state = project.getState();
    }
}

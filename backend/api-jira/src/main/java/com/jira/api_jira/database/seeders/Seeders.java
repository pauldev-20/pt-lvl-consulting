package com.jira.api_jira.database.seeders;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.jira.api_jira.models.Project;
import com.jira.api_jira.models.User;
import com.jira.api_jira.repository.UserRepository;
import com.jira.api_jira.services.ProjectService;
import com.jira.api_jira.services.UserService;

@Component
public class Seeders implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
        System.out.println("Success user seeder");
    }

    private void seedUsers() {
        if (this.userService.getUserByEmail("miguelliberato@gmail.com") != null) {
            return;
        }
        User user = new User();
        user.setId(1);
        user.setNames("Miguel Ángel");
        user.setLastNames("Liberato Carmín");
        user.setNameCompany("LVL Consulting");
        user.setPost("CEO LVL Consulting");
        user.setPhoneNumber("+51 987654321");
        user.setEmail("miguelliberato@gmail.com");
        user.setPassword("12345678");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        User savedUser = this.userRepository.save(user);
        seedProjects(savedUser);
    }

    private void seedProjects(User user) {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("Proyecto de App", "ATA-1", "PLANIFICACION", "Ok", "2024-06-01", "2024-07-01", false,
                user));
        projects.add(
                new Project("Diseño dd RR.SS.", "PA-21", "EN_CURSO", "Ok", "2024-05-01", "2024-06-01", false, user));
        projects.add(new Project("Progrmacion de App", "PA-2", "EN_REVISION", "Ok", "2024-07-01", "2024-07-10", false,
                user));
        projects.add(new Project("Control de Calidad", "ATA-1", "FINALIZADO", "Ok", "2024-04-01", "2024-05-01", false,
                user));
        projects.add(new Project("Notificaciones de App", "ATA-1", "PLANIFICACION", "Ok", "2024-04-01", "2024-04-20",
                false, user));
        projects.add(new Project("Pago de ventanilla", "PA-2", "EN_REVISION", "Ok", "2024-06-01", "2024-07-01", false,
                user));
        
        for (Project project : projects) {
            projectService.saveProject(project);
        }
    }
}
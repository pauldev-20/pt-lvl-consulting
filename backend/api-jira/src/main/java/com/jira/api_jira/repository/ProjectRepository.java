package com.jira.api_jira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jira.api_jira.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query(value = "SELECT p FROM Project p WHERE p.user.id =:userId")
    List<Project> findProjectsByIdUser(@Param("userId") Integer userId);
}

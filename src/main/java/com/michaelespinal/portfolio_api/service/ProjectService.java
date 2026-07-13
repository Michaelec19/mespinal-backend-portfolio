package com.michaelespinal.portfolio_api.service;

import com.michaelespinal.portfolio_api.model.Project;
import com.michaelespinal.portfolio_api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        if (project.getTitle() == null || project.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("The title cannot be null or empty");
        }

        if (project.getDescription() == null || project.getDescription().length() < 10) {
            throw new IllegalArgumentException("The description must be at least 10 characters long");
        }

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}


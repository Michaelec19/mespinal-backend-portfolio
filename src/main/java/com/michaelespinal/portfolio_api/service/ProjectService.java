package com.michaelespinal.portfolio_api.service;

import com.michaelespinal.portfolio_api.model.Project;
import com.michaelespinal.portfolio_api.model.Skill;
import com.michaelespinal.portfolio_api.repository.ProjectRepository;
import com.michaelespinal.portfolio_api.repository.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SkillRepository skillRepository;

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

    public Project addSkillToProject(Long projectId, Long skillId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("The Project with ID " + projectId + " does not exist"));

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new IllegalArgumentException("The Skill with ID " + skillId + " does not exist"));

        project.getSkills().add(skill);

        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project updatedProject) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The project ID " + id + " does not exist."));

        if (updatedProject.getTitle() == null || updatedProject.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("The title cannot be null or empty");
        }
        if (updatedProject.getDescription() == null || updatedProject.getDescription().length() < 10) {
            throw new IllegalArgumentException("The description must be at least 10 characters long");
        }

        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setGithubUrl(updatedProject.getGithubUrl());
        existingProject.setLiveUrl(updatedProject.getLiveUrl());

        return projectRepository.save(existingProject);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The project ID " + id + " does not exist."));
        projectRepository.delete(project);
    }
}


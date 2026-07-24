package com.michaelespinal.portfolio_api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.michaelespinal.portfolio_api.model.Project;
import com.michaelespinal.portfolio_api.model.Skill;
import com.michaelespinal.portfolio_api.repository.ProjectRepository;
import com.michaelespinal.portfolio_api.repository.SkillRepository;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void saveProject_Success() {
        Project p = new Project();
        p.setTitle("My Portfolio");
        p.setDescription("A long description of more than 10 characters");

        when(projectRepository.save(any(Project.class))).thenReturn(p);

        Project saved = projectService.saveProject(p);
        assertNotNull(saved);
    }

    @Test
    void saveProject_ShortDescription_ThrowsException() {
        Project p = new Project();
        p.setTitle("Title");
        p.setDescription("Short");

        assertThrows(IllegalArgumentException.class, () -> projectService.saveProject(p));
    }

    @Test
    void updateProject_Success() {
        Project existing = new Project();
        existing.setTitle("Old Title");
        
        Project updatedData = new Project();
        updatedData.setTitle("New Title");
        updatedData.setDescription("Valid Description");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(projectRepository.save(any(Project.class))).thenReturn(existing);

        Project result = projectService.updateProject(1L, updatedData);
        assertEquals("New Title", result.getTitle());
    }

    @Test
    void addSkillToProject_Success() {
        Project p = new Project();
        p.setSkills(new java.util.HashSet<>());
        Skill s = new Skill();

        when(projectRepository.findById(1L)).thenReturn(Optional.of(p));
        when(skillRepository.findById(2L)).thenReturn(Optional.of(s));
        when(projectRepository.save(any(Project.class))).thenReturn(p);

        Project result = projectService.addSkillToProject(1L, 2L);
        assertEquals(1, result.getSkills().size());
    }

    @Test
    void deleteProject_Success() {
        Project p = new Project();
        when(projectRepository.findById(1L)).thenReturn(Optional.of(p));
        
        projectService.deleteProject(1L);
        verify(projectRepository, times(1)).delete(p);
    }
}
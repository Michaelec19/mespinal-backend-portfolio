package com.michaelespinal.portfolio_api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.michaelespinal.portfolio_api.model.Skill;
import com.michaelespinal.portfolio_api.repository.SkillRepository;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService;

    @Test
    void saveSkill_Success() {
        Skill s = new Skill();
        s.setName("Java");
        s.setCategory("Backend");

        when(skillRepository.save(any(Skill.class))).thenReturn(s);
        assertNotNull(skillService.saveSkill(s));
    }

    @Test
    void saveSkill_MissingCategory_ThrowsException() {
        Skill s = new Skill();
        s.setName("Java");
        s.setCategory("");

        assertThrows(IllegalArgumentException.class, () -> skillService.saveSkill(s));
    }

    @Test
    void getAllSkills_Success() {
        when(skillRepository.findAll()).thenReturn(List.of(new Skill()));
        assertEquals(1, skillService.getAllSkills().size());
    }

    @Test
    void deleteSkill_Success() {
        Skill s = new Skill();
        when(skillRepository.findById(1L)).thenReturn(Optional.of(s));
        
        skillService.deleteSkill(1L);
        verify(skillRepository, times(1)).delete(s);
    }
}
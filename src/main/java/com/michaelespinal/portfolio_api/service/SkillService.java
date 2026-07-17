package com.michaelespinal.portfolio_api.service;

import com.michaelespinal.portfolio_api.model.Project;
import com.michaelespinal.portfolio_api.model.Skill;
import com.michaelespinal.portfolio_api.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public Skill saveSkill(Skill skill) {
        if (skill.getName() == null || skill.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be null or empty");
        }

        if (skill.getCategory() == null || skill.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("The category cannot be null or empty");
        }

        return skillRepository.save(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public void deleteSkill(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The skill ID " + id + " don`t exist."));
        skillRepository.delete(skill);
    }


    
}

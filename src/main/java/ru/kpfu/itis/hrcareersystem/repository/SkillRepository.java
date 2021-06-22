package ru.kpfu.itis.hrcareersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.hrcareersystem.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}

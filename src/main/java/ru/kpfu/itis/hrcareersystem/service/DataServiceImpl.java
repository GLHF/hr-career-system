package ru.kpfu.itis.hrcareersystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.hrcareersystem.dto.EmployeeLoadDto;
import ru.kpfu.itis.hrcareersystem.dto.PositionLoadDto;
import ru.kpfu.itis.hrcareersystem.dto.SkillLoadDto;
import ru.kpfu.itis.hrcareersystem.model.*;
import ru.kpfu.itis.hrcareersystem.repository.EmployeeRepository;
import ru.kpfu.itis.hrcareersystem.repository.PositionRepository;
import ru.kpfu.itis.hrcareersystem.repository.SkillRepository;
import ru.kpfu.itis.hrcareersystem.service.api.DataService;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final SkillRepository skillRepository;

    @Override
    @Transactional
    public Employee saveEmployee(EmployeeLoadDto employeeLoadDto) {
        Employee employee = Employee.builder()
                .id(employeeLoadDto.getId())
                .firstName(employeeLoadDto.getFirstName())
                .lastName(employeeLoadDto.getLastName())
                .skills(employeeLoadDto.getSkills().stream()
                        .map(skillLoadDto -> EmployeeSkill.builder()
                                .skill(skillRepository.getOne(skillLoadDto.getId()))
                                .scale(skillLoadDto.getScale()).build())
                        .collect(Collectors.toSet()))
                .build();
        employee.getSkills().forEach(skill -> skill.setEmployee(employee));
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    @Transactional
    public Skill saveSkill(SkillLoadDto skillLoadDto) {
        Skill skill = Skill.builder()
                .id(skillLoadDto.getId())
                .title(skillLoadDto.getTitle())
                .build();
        return skillRepository.saveAndFlush(skill);
    }

    @Override
    @Transactional
    public Position savePosition(PositionLoadDto positionLoadDto) {
        Position position = Position.builder()
                .id(positionLoadDto.getId())
                .title(positionLoadDto.getTitle())
                .requirements(positionLoadDto.getRequirements().stream()
                        .map(skillLoadDto -> SkillRequirement.builder()
                                .skill(skillRepository.getOne(skillLoadDto.getId()))
                                .scale(skillLoadDto.getScale()).build())
                        .collect(Collectors.toSet()))
                .build();
        position.getRequirements().forEach(skill -> skill.setPosition(position));
        return positionRepository.saveAndFlush(position);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deletePosition(Long id) {
        positionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Employee getEmployee(Long id) {
        return employeeRepository.getOne(id);
    }

    @Override
    @Transactional
    public Skill getSkill(Long id) {
        return skillRepository.getOne(id);
    }

    @Override
    @Transactional
    public Position getPosition(Long id) {
        return positionRepository.getOne(id);
    }
}

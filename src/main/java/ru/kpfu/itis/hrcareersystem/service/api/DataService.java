package ru.kpfu.itis.hrcareersystem.service.api;

import ru.kpfu.itis.hrcareersystem.dto.EmployeeLoadDto;
import ru.kpfu.itis.hrcareersystem.dto.PositionLoadDto;
import ru.kpfu.itis.hrcareersystem.dto.SkillLoadDto;
import ru.kpfu.itis.hrcareersystem.model.Employee;
import ru.kpfu.itis.hrcareersystem.model.Position;
import ru.kpfu.itis.hrcareersystem.model.Skill;

public interface DataService {
    Employee saveEmployee(EmployeeLoadDto employeeLoadDto);
    Skill saveSkill(SkillLoadDto skillLoadDto);
    Position savePosition(PositionLoadDto positionLoadDto);

    void deleteEmployee(Long id);
    void deleteSkill(Long id);
    void deletePosition(Long id);

    Employee getEmployee(Long id);
    Skill getSkill(Long id);
    Position getPosition(Long id);
}

package ru.kpfu.itis.hrcareersystem.service.api;

import ru.kpfu.itis.hrcareersystem.dto.response.EmployeesForPositionDto;
import ru.kpfu.itis.hrcareersystem.dto.response.PositionsForEmployeeDto;

public interface HrService {
    PositionsForEmployeeDto getPositionsForEmployee(Long employeeId);
    EmployeesForPositionDto getEmployeesForPosition(Long positionId);
}

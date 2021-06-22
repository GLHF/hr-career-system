package ru.kpfu.itis.hrcareersystem.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.hrcareersystem.dto.response.EmployeesForPositionDto;
import ru.kpfu.itis.hrcareersystem.dto.response.PositionsForEmployeeDto;
import ru.kpfu.itis.hrcareersystem.service.api.HrService;

@Api(tags = "API основного функционала")
@RestController
@RequestMapping("/hr")
@Slf4j
@RequiredArgsConstructor
public class HrController {
    private final HrService hrService;

    @Operation(summary = "Получение наииболее оптимальных карьерных направления сотрудника",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Направления получены",
                            content = {@Content(schema = @Schema(implementation = PositionsForEmployeeDto.class))})
            })
    @GetMapping("/career/employee")
    public ResponseEntity getPosititons(@RequestParam Long employeeId) {
        try {
            return ResponseEntity.ok(hrService.getPositionsForEmployee(employeeId));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }

    @Operation(summary = "Получение потанцеальных сотрудников для должности",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Должности получены",
                            content = {@Content(schema = @Schema(implementation = EmployeesForPositionDto.class))})
            })
    @GetMapping("/employees/position")
    public ResponseEntity getEmployees(@RequestParam Long positionId) {
        try {
            return ResponseEntity.ok(hrService.getEmployeesForPosition(positionId));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }
}

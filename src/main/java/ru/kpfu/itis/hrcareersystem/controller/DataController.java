package ru.kpfu.itis.hrcareersystem.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.hrcareersystem.dto.EmployeeLoadDto;
import ru.kpfu.itis.hrcareersystem.dto.PositionLoadDto;
import ru.kpfu.itis.hrcareersystem.dto.SkillLoadDto;
import ru.kpfu.itis.hrcareersystem.service.api.DataService;

@Api(tags = "API для работы с сущностями")
@RestController
@RequestMapping("/data")
@Slf4j
@RequiredArgsConstructor
public class DataController {
    private final DataService dataService;

    @Operation(summary = "Сохранение/обновлениме сотрудника",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Сотрудник загружен")
            })
    @PostMapping("/employee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeLoadDto employeeLoadDto) {
        return ResponseEntity.ok(dataService.saveEmployee(employeeLoadDto));
    }

    @Operation(summary = "Удаление сотрудника",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Сотрудник удален")
            })
    @DeleteMapping("/employee")
    public ResponseEntity deleteEmployee(@RequestParam Long employeeId) {
        try {
            dataService.deleteEmployee(employeeId);
            return ResponseEntity.ok("Deleted");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }

    @Operation(summary = "Получение сотрудника",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Сотрудник получен")
            })
    @GetMapping("/employee")
    public ResponseEntity getEmployee(@RequestParam Long employeeId) {
        try {
            return ResponseEntity.ok(dataService.getEmployee(employeeId));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }



    @Operation(summary = "Сохранение/обновлениме навыка",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Навык загружен")
            })
    @PostMapping("/skill")
    public ResponseEntity saveSkill(@RequestBody SkillLoadDto skillLoadDto) {
        return ResponseEntity.ok(dataService.saveSkill(skillLoadDto));
    }

    @Operation(summary = "Удаление навыка",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Навык загружен")
            })
    @DeleteMapping("/skill")
    public ResponseEntity deleteSkill(@RequestParam Long skillId) {
        try {
            dataService.deleteSkill(skillId);
            return ResponseEntity.ok("Deleted");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }

    @Operation(summary = "Получение навыка",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Навык получен")
            })
    @GetMapping("/skill")
    public ResponseEntity getSkill(@RequestParam Long skillId) {
        try {
            return ResponseEntity.ok(dataService.getSkill(skillId));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }



    @Operation(summary = "Сохранение/обновлениме должности",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Должность загружена")
            })
    @PostMapping("/position")
    public ResponseEntity savePosition(@RequestBody PositionLoadDto positionLoadDto) {
        return ResponseEntity.ok(dataService.savePosition(positionLoadDto));
    }

    @Operation(summary = "Удаление должности",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Должность удалена")
            })
    @DeleteMapping("/position")
    public ResponseEntity deletePosition(@RequestParam Long positionId) {
        try {
            dataService.deletePosition(positionId);
            return ResponseEntity.ok("Deleted");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }

    @Operation(summary = "Получение должности",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Должность получена")
            })
    @GetMapping("/position")
    public ResponseEntity getPosition(@RequestParam Long positionId) {
        try {
            return ResponseEntity.ok(dataService.getPosition(positionId));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }
}

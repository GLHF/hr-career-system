package ru.kpfu.itis.hrcareersystem.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Информация о наиболее подходящих для должности сотрудников")
public class EmployeesForPositionDto {
    private Long positionId;
    private String positionTitle;
    private List<EmployeeForPosition> employees;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class EmployeeForPosition {
        private Long employeeId;

        private Double matchCoefficient;
        private Double overqualificationCoefficient;
    }
}

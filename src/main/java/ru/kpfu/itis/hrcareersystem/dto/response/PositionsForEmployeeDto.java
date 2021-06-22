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
@Schema(description = "Информация о наиболее подходящих сотруднику карьерных направлениях")
public class PositionsForEmployeeDto {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private List<PositionForEmployee> positions;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PositionForEmployee {
        private Long positionId;

        private Double matchCoefficient;
        private Double overqualificationCoefficient;
    }
}

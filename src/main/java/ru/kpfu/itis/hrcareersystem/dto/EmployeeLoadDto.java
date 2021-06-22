package ru.kpfu.itis.hrcareersystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeLoadDto {
    private Long id;
    private String firstName;
    private String lastName;

    @Builder.Default
    private Set<SkillLoadDto> skills = new HashSet<>();
}

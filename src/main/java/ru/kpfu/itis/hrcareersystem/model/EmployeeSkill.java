package ru.kpfu.itis.hrcareersystem.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(exclude ={"employee"})
@ToString(exclude ={"employee"})
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_skill")
public class EmployeeSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    @Column(name = "scale")
    private Integer scale;
}

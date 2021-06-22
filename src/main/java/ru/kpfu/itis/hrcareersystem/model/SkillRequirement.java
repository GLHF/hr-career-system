package ru.kpfu.itis.hrcareersystem.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(exclude ={"position"})
@ToString(exclude ={"position"})
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "position_skill")
public class SkillRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    @Column(name = "scale")
    private Integer scale;
}

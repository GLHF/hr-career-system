package ru.kpfu.itis.hrcareersystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.hrcareersystem.dto.response.EmployeesForPositionDto;
import ru.kpfu.itis.hrcareersystem.dto.response.PositionsForEmployeeDto;
import ru.kpfu.itis.hrcareersystem.model.Employee;
import ru.kpfu.itis.hrcareersystem.model.Position;
import ru.kpfu.itis.hrcareersystem.repository.EmployeeRepository;
import ru.kpfu.itis.hrcareersystem.repository.PositionRepository;
import ru.kpfu.itis.hrcareersystem.repository.SkillRepository;
import ru.kpfu.itis.hrcareersystem.service.api.HrService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HrServiceImpl implements HrService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final SkillRepository skillRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    private final String SELECT_EMPLOYEE_METRICS = "WITH positions_reqs AS(" +
            " SELECT " +
            "                           pos_data.position, " +
            "                           pos_data.employee, " +
            "                           pos_data.pos_scale, " +
            "                           MAX(pos_data.emp_scale) as emp_scale, " +
            "                           pos_data.skill_id " +
            "                           FROM " +
            "    (SELECT ps.position_id        as position, " +
            "           es.employee_id        as employee, " +
            "           COALESCE(ps.scale, 0) as pos_scale, " +
            "           COALESCE(es.scale, 0) as emp_scale, " +
            "           es.skill_id " +
            "    FROM position_skill ps " +
            "             LEFT JOIN (SELECT * FROM employee_skill) es " +
            "                       ON ps.skill_id = es.skill_id " +
            "    WHERE position_id = ?1 " +
            "    UNION ALL " +
            "    SELECT position_id as position, employee.id as employee, scale as pos_scale, 0 as emp_scale, skill_id FROM employee" +
            "                                                                                                                   FULL OUTER JOIN (SELECT * FROM position_skill WHERE position_id = ?1) as position_skill" +
            "                                                                                                                                   ON TRUE" +
            "    ORDER BY skill_id) as pos_data" +
            "    GROUP BY position, employee, pos_scale, emp_scale, skill_id" +
            ")" +
            "SELECT qualified.employee," +
            "       COALESCE(qualified.qualified, 0)         as qualified," +
            "       COALESCE(overqualified.overqualified, 0) as overqualified" +
            " FROM ((SELECT position                                                               as position," +
            "              employee                                                               as employee," +
            "              COALESCE(sqrt(sum(pow(COALESCE(pos_scale, 0) - COALESCE(emp_scale, 0), 2))), 0) as qualified" +
            "       FROM positions_reqs" +
            "       GROUP BY position, employee) as qualified" +
            "         LEFT JOIN" +
            "     (SELECT ps.position_id                                      as position," +
            "             es.employee_id                                      as employee," +
            "             COALESCE(sqrt(sum(pow(ps.scale - es.scale, 2))), 0) as overqualified" +
            "      FROM position_skill ps" +
            "               LEFT JOIN employee_skill es" +
            "                         ON ps.skill_id = es.skill_id" +
            "      WHERE position_id = ?1" +
            "        AND es.scale > ps.scale" +
            "      GROUP BY position, employee" +
            "      ORDER BY overqualified) as overqualified" +
            "     ON qualified.employee = overqualified.employee)" +
            "ORDER BY COALESCE(qualified, 0) * COALESCE(overqualified, 0)" +
            "LIMIT 10;";

    private final String SELECT_POSITION_METRIC = "WITH positions_reqs AS(" +
            "SELECT " +
            "                           pos_data.position, " +
            "                           pos_data.employee, " +
            "                           pos_data.pos_scale, " +
            "                           MAX(pos_data.emp_scale) as emp_scale, " +
            "                           pos_data.skill_id " +
            "                           FROM " +
            "    (SELECT ps.position_id        as position, " +
            "           es.employee_id        as employee, " +
            "           COALESCE(ps.scale, 0) as pos_scale, " +
            "           COALESCE(es.scale, 0) as emp_scale, " +
            "           es.skill_id " +
            "    FROM position_skill ps " +
            "             LEFT JOIN (SELECT * FROM employee_skill) es " +
            "                       ON ps.skill_id = es.skill_id " +
            "    WHERE position_id = ?1 " +
            "    UNION ALL " +
            "    SELECT position_id as position, employee.id as employee, scale as pos_scale, 0 as emp_scale, skill_id FROM employee" +
            "                                                                                                                   FULL OUTER JOIN (SELECT * FROM position_skill WHERE position_id = ?1) as position_skill" +
            "                                                                                                                                   ON TRUE" +
            "    ORDER BY skill_id) as pos_data" +
            "    GROUP BY position, employee, pos_scale, emp_scale, skill_id" +
            ")" +
            "SELECT qualified.employee," +
            "       COALESCE(qualified.qualified, 0)         as qualified," +
            "       COALESCE(overqualified.overqualified, 0) as overqualified" +
            " FROM ((SELECT position                                   as position," +
            "              employee                                    as employee," +
            "              COALESCE(sqrt(sum(pow(pos_scale - emp_scale, 2))), 0) as qualified" +
            "       FROM positions_reqs" +
            "       GROUP BY position, employee) as qualified" +
            "         LEFT JOIN" +
            "     (SELECT ps.position_id                                      as position," +
            "             es.employee_id                                      as employee," +
            "             COALESCE(sqrt(sum(pow(ps.scale - es.scale, 2))), 0) as overqualified" +
            "      FROM position_skill ps" +
            "               LEFT JOIN employee_skill es" +
            "                         ON ps.skill_id = es.skill_id" +
            "      WHERE employee_id = ?1" +
            "        AND es.scale > ps.scale" +
            "      GROUP BY position, employee" +
            "      ORDER BY overqualified) as overqualified" +
            "     ON qualified.position = overqualified.position)" +
            "ORDER BY COALESCE(qualified, 0) * COALESCE(overqualified, 0)" +
            "LIMIT 10;";

    @Override
    @Transactional
    public PositionsForEmployeeDto getPositionsForEmployee(Long employeeId) {
        Query query = entityManager.createNativeQuery(SELECT_EMPLOYEE_METRICS);
        query.setParameter(1, employeeId);
        List<PositionsForEmployeeDto.PositionForEmployee> positionsForEmployees = (List<PositionsForEmployeeDto.PositionForEmployee>)
                query.getResultStream()
                .map(result -> {
                    return PositionsForEmployeeDto.PositionForEmployee.builder()
                            .positionId(((Object[])result)[0] != null ? Long.parseLong(((Object[])result)[0].toString()) : -1)
                            .matchCoefficient(((Object[])result)[1] != null
                                    ? Double.parseDouble(((Object[])result)[1].toString()) : 0)
                            .overqualificationCoefficient(((Object[])result)[2] != null
                                    ? Double.parseDouble(((Object[])result)[2].toString()) : 0)
                            .build();
                })
                .collect(Collectors.toList());
        final Employee employee = employeeRepository.getOne(employeeId);
        return PositionsForEmployeeDto.builder()
                .employeeId(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .positions(positionsForEmployees)
                .build();
    }

    @Override
    @Transactional
    public EmployeesForPositionDto getEmployeesForPosition(Long positionId) {
        Query query = entityManager.createNativeQuery(SELECT_POSITION_METRIC);
        query.setParameter(1, positionId);
        List<EmployeesForPositionDto.EmployeeForPosition> employees = (List<EmployeesForPositionDto.EmployeeForPosition>)
                query.getResultStream()
                        .map(result -> {
                            return EmployeesForPositionDto.EmployeeForPosition.builder()
                                    .employeeId(((Object[])result)[0] != null ? Long.parseLong(((Object[])result)[0].toString()) : -1)
                                    .matchCoefficient(((Object[])result)[1] != null
                                            ? Double.parseDouble(((Object[])result)[1].toString()) : 0)
                                    .overqualificationCoefficient(((Object[])result)[2] != null
                                            ? Double.parseDouble(((Object[])result)[2].toString()) : 0)
                                    .build();
                        })
                        .collect(Collectors.toList());
        final Position position = positionRepository.getOne(positionId);
        return EmployeesForPositionDto.builder()
                .positionId(position.getId())
                .positionTitle(position.getTitle())
                .employees(employees)
                .build();
    }
}

package ru.kpfu.itis.hrcareersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.hrcareersystem.dto.response.PositionsForEmployeeDto;
import ru.kpfu.itis.hrcareersystem.model.Position;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}

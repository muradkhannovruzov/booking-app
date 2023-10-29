package com.example.providerms.repository;

import com.example.providerms.domain.BreakSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BreakScheduleRepository extends JpaRepository<BreakSchedule, Long> {
    List<BreakSchedule> findAllByWorkScheduleId(Long scheduleId);
}

package com.example.providerms.repository;

import com.example.providerms.domain.BreakSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreakScheduleRepository extends JpaRepository<BreakSchedule, Long> {
}

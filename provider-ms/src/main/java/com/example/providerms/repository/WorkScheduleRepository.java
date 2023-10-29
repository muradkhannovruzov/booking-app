package com.example.providerms.repository;

import com.example.providerms.domain.WorkSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {
    Page<WorkSchedule> findAllByProviderId(Pageable pageable, Long providerId);

    Optional<WorkSchedule> findByIdAndProviderId(Long id, Long providerId);
}

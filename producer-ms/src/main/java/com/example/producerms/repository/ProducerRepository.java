package com.example.producerms.repository;

import com.example.producerms.domain.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Producer findByUsername(String username);
}

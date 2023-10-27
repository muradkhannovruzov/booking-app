package com.example.servicecatalogms.repository;

import com.example.servicecatalogms.domain.ServiceCatalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceCatalogRepository extends JpaRepository<ServiceCatalog, Long> {
    Page<ServiceCatalog> findAllByProviderId(Long providerId, Pageable pageable);
}

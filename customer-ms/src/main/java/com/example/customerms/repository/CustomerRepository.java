package com.example.customerms.repository;

import com.example.customerms.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    Optional<Customer> findById(Long id);
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByUsernameAndEnabledIsTrue(String username);

    boolean existsByEmailAndEnabledIsTrue(String email);
    boolean existsByPhoneAndEnabledIsTrue(String phone);
}

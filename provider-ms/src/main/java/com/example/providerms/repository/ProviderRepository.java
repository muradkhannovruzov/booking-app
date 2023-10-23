package com.example.providerms.repository;

import com.example.providerms.domain.Provider;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    @Override
    Optional<Provider> findById(Long id);
    Optional<Provider> findByUsername(String username);
    Optional<Provider> findByUsernameAndEnabledIsTrue(String username);

    boolean existsByEmailAndEnabledIsTrue(String email);
    boolean existsByPhoneAndEnabledIsTrue(String phone);
}

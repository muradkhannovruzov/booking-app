package com.example.providerms.repository;

import com.example.providerms.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("""
    select t from Token as t
    inner join Provider as p
    on p.id = t.provider.id
    where p.id = :providerId
        and (t.expired = false or t.revoked = false)
""")
    List<Token> findAllValidTokensByProvider(Long providerId);

    Optional<Token> findByToken(String token);

}

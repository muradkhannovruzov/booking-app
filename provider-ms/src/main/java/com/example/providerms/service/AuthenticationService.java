package com.example.providerms.service;

import com.example.providerms.config.JwtService;
import com.example.providerms.domain.Provider;
import com.example.providerms.dto.auth.SignInRequestDto;
import com.example.providerms.dto.auth.SignInResponseDto;
import com.example.providerms.dto.auth.SignUpRequestDto;
import com.example.providerms.exception.BaseException;
import com.example.providerms.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ProviderRepository providerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public SignInResponseDto signUp(SignUpRequestDto dto) {
        var user = Provider.builder() // todo : user modelMapper or custom mapper
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .phone(dto.getPhone())
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        providerRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return new SignInResponseDto(jwtToken);
    }

    public boolean isPresentUsername(String username){
        return providerRepository.findByUsername(username).isPresent();
    }

    public SignInResponseDto signIn(SignInRequestDto dto) {
        log.info("AuthenticationService -> signIn | " + dto);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );

        var user = providerRepository.findByUsername(dto.getUsername())
                .orElseThrow(() ->
                        BaseException.notFound(Provider.class.getSimpleName(), "username", dto.getUsername()));

        var jwtToken = jwtService.generateToken(user);

        return new SignInResponseDto(jwtToken);
    }

    public Provider getUserFromToken(String token) {

        Long userId = jwtService.extractClaim
                (token, claims -> claims.get("userId", Long.class));

        return providerRepository.findById(userId)
                .orElseThrow(
                        () -> BaseException.notFound(
                                Provider.class.getSimpleName(),
                                "userId",
                                userId.toString()));
    }

}

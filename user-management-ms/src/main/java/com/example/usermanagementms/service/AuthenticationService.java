package com.example.usermanagementms.service;

import com.example.usermanagementms.config.JwtService;
import com.example.usermanagementms.domain.Role;
import com.example.usermanagementms.domain.User;
import com.example.usermanagementms.dto.auth.SignInRequestDto;
import com.example.usermanagementms.dto.auth.SignInResponseDto;
import com.example.usermanagementms.dto.auth.SignUpRequestDto;
import com.example.usermanagementms.exception.BaseException;
import com.example.usermanagementms.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public SignInResponseDto signUp(SignUpRequestDto dto) {
        var user = User.builder()
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .role(dto.getRole()) // todo: Can't create ADMIN (check role)
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return new SignInResponseDto(jwtToken);
    }

    public boolean isPresentUsername(String username){
        return userRepository.findByUsername(username).isPresent();
    }

    public SignInResponseDto signIn(SignInRequestDto dto) {
        log.info("AuthenticationService -> signIn | " + dto);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );

        var user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() ->
                        BaseException.notFound(User.class.getSimpleName(), "username", dto.getUsername()));

        var jwtToken = jwtService.generateToken(user);

        return new SignInResponseDto(jwtToken);
    }
}

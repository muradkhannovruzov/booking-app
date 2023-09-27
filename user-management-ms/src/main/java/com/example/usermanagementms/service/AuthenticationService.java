package com.example.usermanagementms.service;

import com.example.usermanagementms.config.JwtService;
import com.example.usermanagementms.domain.User;
import com.example.usermanagementms.dto.auth.SignInRequestDto;
import com.example.usermanagementms.dto.auth.SignInResponseDto;
import com.example.usermanagementms.dto.auth.SignUpRequestDto;
import com.example.usermanagementms.repository.AuthorityRepository;
import com.example.usermanagementms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public SignInResponseDto signUp(SignUpRequestDto dto) {
        var user = User.builder()
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        authorityRepository.findById(1L)
                .ifPresent(userAuthority ->
                        user.getAuthorities().add(userAuthority));

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return new SignInResponseDto(jwtToken);
    }

    public SignInResponseDto signIn(SignInRequestDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );

        var user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return new SignInResponseDto(jwtToken);
    }
}

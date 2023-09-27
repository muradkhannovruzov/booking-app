package com.example.usermanagementms.controller;


import com.example.usermanagementms.dto.auth.SignInResponseDto;
import com.example.usermanagementms.dto.auth.SignInRequestDto;
import com.example.usermanagementms.dto.auth.SignUpRequestDto;
import com.example.usermanagementms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignInResponseDto> signUp(
            @RequestBody SignUpRequestDto dto
    ){
        return ResponseEntity.ok(authService.signUp(dto));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponseDto> signIn(
            @RequestBody SignInRequestDto dto
    ){
        return ResponseEntity.ok(authService.signIn(dto));
    }

}
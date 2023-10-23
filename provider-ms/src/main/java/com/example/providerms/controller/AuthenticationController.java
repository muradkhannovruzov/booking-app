package com.example.providerms.controller;


import com.example.providerms.dto.auth.SignInRequestDto;
import com.example.providerms.dto.auth.SignInResponseDto;
import com.example.providerms.dto.auth.SignUpRequestDto;
import com.example.providerms.response.BaseResponse;
import com.example.providerms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth/provider")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/sign-up")
    public BaseResponse<SignInResponseDto> signUp(
            @RequestBody SignUpRequestDto dto
    ){
        return BaseResponse.success(authService.signUp(dto));
    }

    @PostMapping("/sign-in")
    public BaseResponse<SignInResponseDto> signIn(
            @RequestBody SignInRequestDto dto
    ){
        log.info("sign-in request:" + dto);
        return BaseResponse.success(authService.signIn(dto));
    }

}
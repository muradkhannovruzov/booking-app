package com.example.providerms.controller;


import com.example.providerms.dto.auth.SignInRequestDto;
import com.example.providerms.dto.auth.SignInResponseDto;
import com.example.providerms.dto.auth.SignUpRequestDto;
import com.example.providerms.response.BaseResponse;
import com.example.providerms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @GetMapping("/producer-details")
    public String getProducerDetails(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
            UserDetails userDetails = (UserDetails) token.getPrincipal();

            return userDetails.toString();
        }
        return "Unknown producer";
    }

    @GetMapping("/producer-details-v2")
    public String getProducerDetails(@AuthenticationPrincipal Long userId) {
        System.out.println(userId);
        return userId.toString();
    }

}
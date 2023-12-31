package com.example.providerms.utils;

import com.example.providerms.config.JwtService;
import com.example.providerms.domain.Provider;
import com.example.providerms.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import static com.example.providerms.response.enums.ErrorResponseMessages.FORBIDDEN;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    public Provider extractUserFromAuthHeader(String authHeader) {

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String jwt = authHeader.substring(7);
            String userEmail = jwtService.extractUsername(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            return (Provider) userDetails;
        }
        throw BaseException.of(FORBIDDEN);
    }
}

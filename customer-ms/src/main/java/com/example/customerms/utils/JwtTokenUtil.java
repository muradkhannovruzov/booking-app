package com.example.customerms.utils;

import com.example.customerms.config.JwtService;
import com.example.customerms.domain.Customer;
import com.example.customerms.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import static com.example.customerms.response.enums.ErrorResponseMessages.FORBIDDEN;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    public Customer extractUserFromAuthHeader(String authHeader) {

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String jwt = authHeader.substring(7);
            String userEmail = jwtService.extractUsername(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            return (Customer) userDetails;
        }
        throw BaseException.of(FORBIDDEN);
    }
}

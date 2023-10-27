package com.example.servicecatalogms.security;

import com.example.servicecatalogms.config.JwtService;
import com.example.servicecatalogms.domain.ServiceCatalog;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//@Component
@RequiredArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {
    private final JwtService jwtService;
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        System.out.println("CustomPermissionEvaluator hasPermission called");
        if (authentication == null || targetDomainObject == null || !(targetDomainObject instanceof ServiceCatalog)) {
            return false;
        }

        System.out.println(authentication);
        ServiceCatalog serviceCatalog = (ServiceCatalog) targetDomainObject;
        System.out.println(serviceCatalog);

        // Extract the user's provider ID from the JWT token using JwtService
//        String token = ((Jwt) authentication.getPrincipal()).getBody();
//        Long userProviderId = jwtService.extractProviderId(token);

        // Extract the target service's provider ID
        Long serviceProviderId = ((ServiceCatalog) targetDomainObject).getProviderId();

        // Check if the user has permission based on provider ID comparison
//        boolean hasPermission = userProviderId.equals(serviceProviderId);

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}

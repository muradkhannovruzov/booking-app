package com.example.servicecatalogms.config;

import com.example.servicecatalogms.security.CustomPermissionEvaluator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;

//    private final AuthenticationProvider authenticationProvider;
//    private final CustomPermissionEvaluator customPermissionEvaluator;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http
//                .csrf().disable()
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .anyRequest().authenticated()
//                )
//                .sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        http
//                .authorizeRequests()
//                .expressionHandler(new DefaultWebSecurityExpressionHandler() {
//                    @Override
//                    protected PermissionEvaluator getPermissionEvaluator() {
//                        return customPermissionEvaluator;
//                    }
//                });
//
//        return http.build();

        ////////////////////////////////////////////////////////////////////////////////
        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


//        http
//                .authorizeRequests()
//                .expressionHandler(new DefaultWebSecurityExpressionHandler() {
//                    @Override
//                    protected PermissionEvaluator getPermissionEvaluator() {
//                        return customPermissionEvaluator;
//                    }
//                });

        return http.build();
    }
}

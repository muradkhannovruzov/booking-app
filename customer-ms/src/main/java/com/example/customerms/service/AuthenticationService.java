package com.example.customerms.service;

import com.example.customerms.config.JwtService;
import com.example.customerms.domain.Customer;
import com.example.customerms.dto.auth.SignInRequestDto;
import com.example.customerms.dto.auth.SignInResponseDto;
import com.example.customerms.dto.auth.SignUpRequestDto;
import com.example.customerms.exception.BaseException;
import com.example.customerms.repository.CustomerRepository;
import com.example.customerms.response.enums.ErrorResponseMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;


    public SignInResponseDto signUp(SignUpRequestDto dto) {
        checkForExistingCredentials(dto);

        var customer = createProviderEntityObject(dto);
        customerRepository.save(customer);

        var jwtToken = jwtService.generateToken(customer);
        return new SignInResponseDto(jwtToken);
    }

    public boolean isPresentUsername(String username){
        return customerRepository.findByUsernameAndEnabledIsTrue(username)
                .isPresent();
    }

    public boolean isPresentPhone(String phone){
        return customerRepository.existsByPhoneAndEnabledIsTrue(phone);
    }

    public boolean isPresentEmail(String email){
        return customerRepository.existsByEmailAndEnabledIsTrue(email);
    }

    public SignInResponseDto signIn(SignInRequestDto dto) {
        log.info("AuthenticationService -> signIn | " + dto);

        var provider = customerRepository.findByUsernameAndEnabledIsTrue
                        (dto.getUsername()).orElseThrow(() ->
                        BaseException.notFound
                                (Customer.class.getSimpleName(),
                                        "username",
                                        dto.getUsername()));

        checkIfAccountActive(provider);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(provider);

        return new SignInResponseDto(jwtToken);
    }

    public Customer getUserFromToken(String token) {

        Long userId = jwtService.extractClaim
                (token, claims -> claims.get("userId", Long.class));

        return customerRepository.findById(userId)
                .orElseThrow(
                        () -> BaseException.notFound(
                                Customer.class.getSimpleName(),
                                "userId",
                                userId.toString()));
    }

    private Customer createProviderEntityObject(SignUpRequestDto signUpDto) {
        Customer customer = modelMapper.map(signUpDto, Customer.class);
        customer.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        customer.setAccountNonExpired(false);
        customer.setAccountNonLocked(false);
        customer.setCredentialsNonExpired(false);
        customer.setEnabled(false);
        return customer;
    }

    private void checkForExistingCredentials(SignUpRequestDto dto) {
        if (isPresentUsername(dto.getUsername())) {
            throw BaseException.of(ErrorResponseMessages.USERNAME_ALREADY_EXIST);
        }
        if (isPresentPhone(dto.getPhone())) {
            throw BaseException.of(ErrorResponseMessages.PHONE_NUMBER_ALREADY_EXIST);
        }
        if (isPresentEmail(dto.getEmail())) {
            throw BaseException.of(ErrorResponseMessages.EMAIL_ALREADY_REGISTERED);
        }
    }

    private void checkIfAccountActive(Customer provider) {
        if (!provider.isEnabled()
                || !provider.isAccountNonLocked()
                || !provider.isAccountNonExpired()
                || !provider.isCredentialsNonExpired()) {
            throw BaseException.of(ErrorResponseMessages.USER_NOT_ACTIVE);
        }
    }

}

package com.example.providerms.service;

import com.example.providerms.config.JwtService;
import com.example.providerms.domain.Provider;
import com.example.providerms.domain.Token;
import com.example.providerms.domain.enums.TokenType;
import com.example.providerms.dto.auth.SignInRequestDto;
import com.example.providerms.dto.auth.SignInResponseDto;
import com.example.providerms.dto.auth.SignUpRequestDto;
import com.example.providerms.exception.BaseException;
import com.example.providerms.repository.ProviderRepository;
import com.example.providerms.repository.TokenRepository;
import com.example.providerms.response.enums.ErrorResponseMessages;
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

    private final ProviderRepository providerRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;


    public SignInResponseDto signUp(SignUpRequestDto dto) {
        checkForExistingCredentials(dto);

        var provider = createProviderEntityObject(dto);
        var savedProvider = providerRepository.save(provider);
        var jwtToken = jwtService.generateToken(savedProvider);
        saveProviderToken(savedProvider, jwtToken);

        return new SignInResponseDto(jwtToken);
    }



    public boolean isPresentUsername(String username){
        return providerRepository.findByUsernameAndEnabledIsTrue(username)
                .isPresent();
    }

    public SignInResponseDto signIn(SignInRequestDto dto) {
        log.info("AuthenticationService -> signIn | " + dto);

        var provider = providerRepository.findByUsernameAndEnabledIsTrue
                (dto.getUsername()).orElseThrow(() ->
                BaseException.notFound
                        (Provider.class.getSimpleName(),
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
        revokeAllProviderToken(provider);
        saveProviderToken(provider, jwtToken);

        return new SignInResponseDto(jwtToken);
    }

    public boolean isPresentPhone(String phone){
        return providerRepository.existsByPhoneAndEnabledIsTrue(phone);
    }

    public boolean isPresentEmail(String email){
        return providerRepository.existsByEmailAndEnabledIsTrue(email);
    }



    private Provider createProviderEntityObject(SignUpRequestDto signUpDto) {
        Provider provider = modelMapper.map(signUpDto, Provider.class);
        provider.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        provider.setAccountNonExpired(false);
        provider.setAccountNonLocked(false);
        provider.setCredentialsNonExpired(false);
        provider.setEnabled(false);
        return provider;
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

    private void checkIfAccountActive(Provider provider) {
        if (!provider.isEnabled()
                || !provider.isAccountNonLocked()
                || !provider.isAccountNonExpired()
                || !provider.isCredentialsNonExpired()) {
            throw BaseException.of(ErrorResponseMessages.USER_NOT_ACTIVE);
        }
    }

    private void revokeAllProviderToken(Provider provider){
        var validProviderTokens = tokenRepository.findAllValidTokensByProvider(provider.getId());
        if(validProviderTokens.isEmpty())
            return;

        validProviderTokens.forEach(t->{
            t.setExpired(true);
            t.setRevoked(true);
        });

        tokenRepository.saveAll(validProviderTokens);
    }

    private void saveProviderToken(Provider provider, String jwtToken) {
        var token = Token.builder()
                .provider(provider)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();

        tokenRepository.save(token);
    }

}

package com.example.usermanagementms.service.impl;

import com.example.usermanagementms.domain.User;
import com.example.usermanagementms.exception.BaseException;
import com.example.usermanagementms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.usermanagementms.enums.response.ErrorResponseMessages.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetailService authenticating: {}", username);
        System.out.println("Username: " + username);
        System.out.println("User.class.getSimpleName(): " + User.class.getSimpleName());
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null){
            throw BaseException.notFound
                    (User.class.getSimpleName(), "username", username);
        }else {
            if(!user.isEnabled()){
                throw BaseException.of(USER_IS_NOT_ENABLED);
            }
        }
        return user;
    }
}

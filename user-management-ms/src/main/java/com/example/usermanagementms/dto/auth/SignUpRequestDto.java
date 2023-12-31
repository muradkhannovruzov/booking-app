package com.example.usermanagementms.dto.auth;

import com.example.usermanagementms.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
    private String firstname;
    private String lastname;
    private String phone;
    private String username;
    private String password;
    private Role role;
}

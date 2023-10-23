package com.example.providerms.dto.auth;

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
    private String email;
    private String username;
    private String password;
}

package com.innowise.secretSanta.dto;

import com.innowise.secretSanta.model.Role;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Long id;
    private String username;
    private String email;
    private Role role;
}

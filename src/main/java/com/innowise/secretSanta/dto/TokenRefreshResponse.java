package com.innowise.secretSanta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenRefreshResponse {
    private final static String tokenType = "Bearer ";
    private String accessToken;
    private String refreshToken;
}

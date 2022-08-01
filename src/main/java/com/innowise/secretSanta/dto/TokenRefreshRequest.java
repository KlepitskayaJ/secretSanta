package com.innowise.secretSanta.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;
}

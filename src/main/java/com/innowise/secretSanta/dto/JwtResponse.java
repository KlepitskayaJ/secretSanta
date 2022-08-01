package com.innowise.secretSanta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class JwtResponse {
     private String token;
//     private String type = "Bearer";
     // TODO: где генерится токен добавлять
     private String refreshToken;
     private Long id;
     private String email;
     private String role;
}

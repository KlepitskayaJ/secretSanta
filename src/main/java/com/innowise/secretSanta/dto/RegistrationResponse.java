package com.innowise.secretSanta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationResponse {
    private Long accountId;
    private String email;
    private String password;
    private String role;
    private Long userId;
    private String firstName;
    private String lastName;
    private String phone_number;
    private String address;
    private String postcode;
}

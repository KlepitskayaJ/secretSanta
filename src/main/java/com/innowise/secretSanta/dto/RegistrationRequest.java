package com.innowise.secretSanta.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String address;
    private String postcode;
}

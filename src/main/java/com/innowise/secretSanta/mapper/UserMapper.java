package com.innowise.secretSanta.mapper;

import com.innowise.secretSanta.dto.JwtResponse;
import com.innowise.secretSanta.dto.RegistrationRequest;
import com.innowise.secretSanta.model.Account;
import com.innowise.secretSanta.model.User;
import com.innowise.secretSanta.security.RefreshToken;
import com.innowise.secretSanta.security.UserDetailsImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "registrationRequest.first_name", target = "firstName")
    @Mapping(source = "registrationRequest.last_name", target = "lastName")
    @Mapping(source = "registrationRequest.phone_number", target = "phoneNumber")
    @Mapping(source = "registrationRequest.address", target = "address")
    @Mapping(source = "registrationRequest.postcode", target = "postcode")
    @Mapping(source = "account", target = "account")
    User from(RegistrationRequest registrationRequest, Account account);

    @Mapping(source = "token", target = "token")
    @Mapping(source = "refreshToken.token", target = "refreshToken")
    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.username", target = "email")
    @Mapping(source = "role", target = "role")
    JwtResponse from(String token, RefreshToken refreshToken, UserDetailsImpl user, String role);
}

package com.innowise.secretSanta.mapper;

import com.innowise.secretSanta.dto.RegistrationRequest;
import com.innowise.secretSanta.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "registrationRequest.email", target = "email")
    @Mapping(source = "registrationRequest.password", target = "password")
    Account from(RegistrationRequest registrationRequest);


}

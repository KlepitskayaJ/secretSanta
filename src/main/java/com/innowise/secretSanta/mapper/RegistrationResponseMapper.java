package com.innowise.secretSanta.mapper;

import com.innowise.secretSanta.dto.RegistrationResponse;
import com.innowise.secretSanta.model.Account;
import com.innowise.secretSanta.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistrationResponseMapper {

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "account.email", target = "email")
    @Mapping(source = "account.password", target = "password")
    @Mapping(source = "account.role.name", target = "role")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.phoneNumber", target = "phone_number")
    @Mapping(source = "user.address", target = "address")
    @Mapping(source = "user.postcode", target = "postcode")
    RegistrationResponse from(Account account, User user);
}

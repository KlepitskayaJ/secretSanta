package com.innowise.secretSanta.service;

import com.innowise.secretSanta.dto.RegistrationRequest;
import com.innowise.secretSanta.dto.RegistrationResponse;
import com.innowise.secretSanta.model.Account;
import com.innowise.secretSanta.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String email);
    RegistrationResponse saveUser(RegistrationRequest regData);
    Account getAccount(String email);
    User getUser(Account account);
}

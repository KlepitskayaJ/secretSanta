package com.innowise.secretSanta.service;

import com.innowise.secretSanta.dto.RegistrationRequest;
import com.innowise.secretSanta.dto.RegistrationResponse;
import com.innowise.secretSanta.exceptions.EmailDuplicationException;
import com.innowise.secretSanta.mapper.AccountMapper;
import com.innowise.secretSanta.mapper.RegistrationResponseMapper;
import com.innowise.secretSanta.mapper.UserMapper;
import com.innowise.secretSanta.model.Account;
import com.innowise.secretSanta.model.EnumRole;
import com.innowise.secretSanta.model.Role;
import com.innowise.secretSanta.model.User;
import com.innowise.secretSanta.security.UserDetailsImpl;
import com.innowise.secretSanta.repository.AccountRepository;
import com.innowise.secretSanta.repository.RoleRepository;
import com.innowise.secretSanta.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final static String ACCOUNT_NOT_FOUND_MSG = "account with email %s not found";
    private final static String USER_NOT_FOUND_MSG = "user with account id %s not found";
    private final static String EMAIL_IS_TAKEN_MSG = "Email is already taken";

    private final UserMapper userMapper;
    private final AccountMapper accountMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RegistrationResponseMapper registrationResponseMapper;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           AccountMapper accountMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder, RegistrationResponseMapper registrationResponseMapper,
                           AccountRepository accountRepository,
                           UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userMapper = userMapper;
        this.accountMapper = accountMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.registrationResponseMapper = registrationResponseMapper;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Account Not Found with email: " + email));
        return UserDetailsImpl.build(account);
    }

    public RegistrationResponse saveUser(RegistrationRequest regData) throws EmailDuplicationException {
        if (accountRepository.findByEmail(regData.getEmail()).isPresent())
        {
            throw new EmailDuplicationException(EMAIL_IS_TAKEN_MSG);
        }

        Role newAccountRole = roleRepository.getRoleByName(EnumRole.ROLE_USER);
        Account account = accountMapper.from(regData);
        account.setPassword(bCryptPasswordEncoder.encode(regData.getPassword()));
        account.setRole(newAccountRole);
        Account savedAccount = accountRepository.saveAndFlush(account);

        User user = userMapper.from(regData, savedAccount);
        User savedUser =  userRepository.saveAndFlush(user);

        return registrationResponseMapper.from(savedAccount, savedUser);
    }

    @Transactional(readOnly = true)
    public Account getAccount(String email) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isPresent()) { return account.get(); }
        else {
            throw new UsernameNotFoundException(String.format(ACCOUNT_NOT_FOUND_MSG, email));
        }
    }


    public User getUser(Account account) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByAccountId(account.getId());
        if (user.isPresent()) { return user.get(); }
        else {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, user.get()));
        }
    }
}

package com.innowise.secretSanta.security;

import com.innowise.secretSanta.dto.JwtResponse;
import com.innowise.secretSanta.dto.LoginRequest;
import com.innowise.secretSanta.dto.RegistrationRequest;
import com.innowise.secretSanta.mapper.UserMapper;
import com.innowise.secretSanta.model.Account;
import com.innowise.secretSanta.model.User;
import com.innowise.secretSanta.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    private final UserMapper userMapper;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils, RefreshTokenService refreshTokenService, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
        this.userMapper = userMapper;
    }

    public JwtResponse authenticateLoginRequest(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateTokenFromUsername(userDetails.getUsername());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        String role = String.valueOf(userDetails.getAuthorities());
        return userMapper.from(jwt, refreshToken, userDetails, role);
    }
}

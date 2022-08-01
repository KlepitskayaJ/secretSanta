package com.innowise.secretSanta.controller;

import com.innowise.secretSanta.dto.JwtResponse;
import com.innowise.secretSanta.dto.LoginRequest;
import com.innowise.secretSanta.dto.TokenRefreshRequest;
import com.innowise.secretSanta.dto.TokenRefreshResponse;
import com.innowise.secretSanta.security.AuthService;
import com.innowise.secretSanta.security.JwtUtils;
import com.innowise.secretSanta.security.RefreshToken;
import com.innowise.secretSanta.service.RefreshTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/v1/auth")
@Api(value = "auth controller", description = "auth operations")
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthService authService, JwtUtils jwtUtils, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "login new user", response = JwtResponse.class)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse loginResponse = authService.authenticateLoginRequest(loginRequest);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION,  String.format("Bearer %s", loginResponse.getToken()))
                .body(loginResponse);
    }

    @PostMapping("/refreshToken")
    @ApiOperation(value = "generate new refresh token", response = TokenRefreshResponse.class)
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        RefreshToken refreshToken = refreshTokenService.verifyExpiration(refreshTokenService.findByToken(requestRefreshToken));
        String accessToken = jwtUtils.generateTokenFromUsername(refreshToken.getAccount().getEmail());
        return ResponseEntity.ok(new TokenRefreshResponse(accessToken, requestRefreshToken));
    }
}

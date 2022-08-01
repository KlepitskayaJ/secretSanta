package com.innowise.secretSanta.controller;

import com.innowise.secretSanta.dto.JwtResponse;
import com.innowise.secretSanta.dto.LoginRequest;
import com.innowise.secretSanta.dto.RegistrationRequest;
import com.innowise.secretSanta.dto.RegistrationResponse;
import com.innowise.secretSanta.dto.TokenRefreshRequest;
import com.innowise.secretSanta.dto.TokenRefreshResponse;
import com.innowise.secretSanta.exceptions.EmailDuplicationException;
import com.innowise.secretSanta.security.AuthService;
import com.innowise.secretSanta.security.JwtUtils;
import com.innowise.secretSanta.security.RefreshToken;
import com.innowise.secretSanta.service.RefreshTokenService;
import com.innowise.secretSanta.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000/register")
@RequestMapping("/v1/reg")
@Api(value = "registration controller", description = "registration operation")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ApiOperation(value = "register new user", response = RegistrationResponse.class)
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody RegistrationRequest registrationRequest) throws EmailDuplicationException {
        return ResponseEntity.ok().body(userService.saveUser(registrationRequest));
    }

}

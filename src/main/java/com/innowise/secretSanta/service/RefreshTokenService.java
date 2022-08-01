package com.innowise.secretSanta.service;

import com.innowise.secretSanta.exceptions.TokenRefreshException;
import com.innowise.secretSanta.security.RefreshToken;
import com.innowise.secretSanta.repository.AccountRepository;
import com.innowise.secretSanta.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
//    private static final long REFRESH_TOKEN_DURATION_MS = 86400000;
    private static final int REFRESH_TOKEN_DURATION_MS = 100000;

    private final RefreshTokenRepository refreshTokenRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, AccountRepository accountRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.accountRepository = accountRepository;
    }

    public RefreshToken findByToken(String token) {
        return Optional.ofNullable(token)
                .flatMap(refreshTokenRepository::findByToken)
                .orElseThrow(() -> new TokenRefreshException("Refresh token is not in database!"));
    }

    public RefreshToken createRefreshToken(Long accountId) {
        RefreshToken refreshToken = RefreshToken.builder()
                .account(accountRepository.findById(accountId).get())
                .expiryDate(Instant.now().plusMillis(REFRESH_TOKEN_DURATION_MS))
                .token(UUID.randomUUID().toString())
                .build();
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }
}

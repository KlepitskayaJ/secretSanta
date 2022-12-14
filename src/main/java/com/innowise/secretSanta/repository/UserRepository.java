package com.innowise.secretSanta.repository;

import com.innowise.secretSanta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountId(Long accountId);
}

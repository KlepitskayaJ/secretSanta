package com.innowise.secretSanta.repository;

import com.innowise.secretSanta.model.EnumRole;
import com.innowise.secretSanta.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(EnumRole name);
}

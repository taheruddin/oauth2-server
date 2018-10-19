package com.taheruddin.oauth2server.repository;

import com.taheruddin.oauth2server.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

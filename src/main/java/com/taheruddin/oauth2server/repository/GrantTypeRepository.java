package com.taheruddin.oauth2server.repository;

import com.taheruddin.oauth2server.model.GrantType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrantTypeRepository extends JpaRepository<GrantType, Long> {
}

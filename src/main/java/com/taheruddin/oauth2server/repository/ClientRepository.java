package com.taheruddin.oauth2server.repository;

import com.taheruddin.oauth2server.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findById(String clientId);
}

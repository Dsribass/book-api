package com.example.domain.gateway;

import com.example.domain.entity.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientGateway {
    Optional<Client> findById(String id);
    void save(Client client);
    void deleteById(String id);
    void update(Client client);
    List<Client> findAll();
}

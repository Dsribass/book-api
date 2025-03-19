package com.example.infrastructure.repository;

import com.example.domain.entity.Client;
import com.example.domain.gateway.ClientGateway;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryClientRepository implements ClientGateway {
    private final List<Client> clients = new ArrayList<>();

    public Optional<Client> findById(String id) {
        return clients.stream().filter(client -> client.getId().equals(id)).findFirst();
    }

    public void save(Client client) {
        clients.add(client);
    }

    public void deleteById(String id) {
        clients.removeIf(client -> client.getId().equals(id));
    }

    public void update(Client client) {
        deleteById(client.getId());
        save(client);
    }

    public List<Client> findAll() {
        return new ArrayList<>(clients);
    }
}

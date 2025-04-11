package com.example.infrastructure.repository;

import com.example.domain.entity.Client;
import com.example.domain.gateway.ClientGateway;
import com.example.domain.value.Address;
import com.example.domain.value.Email;
import com.example.domain.value.Phone;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryClientRepository implements ClientGateway {
    private final List<Client> clients = new ArrayList<>(
            List.of(
                    new Client(
                            "John Doe",
                            new Email("johndoe@mail.com"),
                            new Phone("+1234567890"),
                            new Address(
                                    "1234",
                                    "Main St",
                                    "Springfield",
                                    "IL",
                                    "USA",
                                    "00000000"
                            ),
                            true
                    )
            )
    );

    public Optional<Client> findByEmail(Email email) {
        return clients.stream().filter(client -> client.getEmail().equals(email)).findFirst();
    }

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

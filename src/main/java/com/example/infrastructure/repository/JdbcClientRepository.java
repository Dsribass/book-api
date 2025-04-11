package com.example.infrastructure.repository;

import com.example.domain.entity.Client;
import com.example.domain.gateway.ClientGateway;
import com.example.domain.value.Email;
import com.example.infrastructure.repository.mappers.ClientRowMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcClientRepository implements ClientGateway {
    private final JdbcTemplate jdbcTemplate;

    public JdbcClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Client> findById(String id) {
        final var sql = """
                SELECT c.id, c.name, c.email, c.phone_number, c.registration_date, c.active,
                       a.street, a.number, a.city, a.state, a.country, a.postal_code
                FROM clients AS c
                JOIN addresses AS a ON c.id = a.client_id
                WHERE c.id = ?
                """;

        return jdbcTemplate.query(
                        sql,
                        new ClientRowMapper(),
                        id
                ).stream()
                .findFirst();
    }

    @Override
    public Optional<Client> findByEmail(Email email) {
        final var sql = """
                SELECT c.id, c.name, c.email, c.phone_number, c.registration_date, c.active,
                       a.street, a.number, a.city, a.state, a.country, a.postal_code
                FROM clients AS c
                JOIN addresses AS a ON c.id = a.client_id
                WHERE c.email = ?
                """;

        return jdbcTemplate.query(
                        sql,
                        new ClientRowMapper(),
                        email.toString()
                ).stream()
                .findFirst();
    }

    @Override
    @Transactional
    public void save(Client client) {
        final var clientSql = """
                INSERT INTO clients (id, name, email, phone_number, registration_date, active)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                clientSql,
                client.getId(),
                client.getName(),
                client.getEmail().toString(),
                client.getPhoneNumber().toString(),
                client.getRegistrationDate().toOffsetDateTime(),
                client.getActive()
        );

        final var addressSql = """
                INSERT INTO addresses (client_id, street, number, city, state, country, postal_code)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        final var address = client.getAddress();

        jdbcTemplate.update(
                addressSql,
                client.getId(),
                address.street(),
                address.number(),
                address.city(),
                address.state(),
                address.country(),
                address.zipCode()
        );
    }

    @Override
    public void deleteById(String id) {
        var sql = """
                DELETE FROM clients
                WHERE id = ?
                """;

        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Client client) {
        var sql = """
                UPDATE clients
                SET name = ?, email = ?, phone_number = ?, registration_date = ?, active = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(
                sql,
                client.getName(),
                client.getEmail().toString(),
                client.getPhoneNumber().toString(),
                client.getRegistrationDate().toOffsetDateTime(),
                client.getActive(),
                client.getId()
        );
    }

    @Override
    public List<Client> findAll() {
        var sql = """
                SELECT c.id, c.name, c.email, c.phone_number, c.registration_date, c.active,
                       a.street, a.number, a.city, a.state, a.country, a.postal_code
                FROM clients AS c
                JOIN addresses AS a ON c.id = a.client_id
                """;

        return jdbcTemplate.query(
                sql,
                new ClientRowMapper()
        );
    }
}

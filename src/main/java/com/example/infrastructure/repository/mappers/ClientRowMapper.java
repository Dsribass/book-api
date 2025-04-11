package com.example.infrastructure.repository.mappers;

import com.example.domain.entity.Client;
import com.example.domain.value.Address;
import com.example.domain.value.Email;
import com.example.domain.value.Phone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class ClientRowMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Client(
                rs.getString("id"),
                rs.getString("name"),
                new Email(rs.getString("email")),
                new Phone(rs.getString("phone_number")),
                new Address(
                        rs.getString("street"),
                        rs.getString("number"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("country"),
                        rs.getString("postal_code")
                ),
                rs.getObject("registration_date", OffsetDateTime.class).toZonedDateTime(),
                rs.getBoolean("active")
        );

    }
}

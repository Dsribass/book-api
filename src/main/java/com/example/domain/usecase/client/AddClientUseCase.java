package com.example.domain.usecase.client;

import com.example.domain.entity.Client;
import com.example.domain.exception.ItemAlreadyExists;
import com.example.domain.gateway.ClientGateway;
import com.example.domain.utils.Logger;
import com.example.domain.utils.NoResult;
import com.example.domain.utils.UseCase;

public class AddClientUseCase extends UseCase<AddClientUseCase.Input, NoResult> {
    private final ClientGateway clientGateway;

    public AddClientUseCase(ClientGateway clientGateway, Logger logger) {
        super(logger);
        logger.setClazz(AddClientUseCase.class);
        this.clientGateway = clientGateway;
    }

    @Override
    protected NoResult run(AddClientUseCase.Input input) {
        if (clientGateway.findById(input.client.getId()).isPresent()) {
            throw new ItemAlreadyExists("Client already exists");
        }

        if (clientGateway.findByEmail(input.client.getEmail()).isPresent()) {
            throw new ItemAlreadyExists("Client with this email already exists");
        }

        clientGateway.save(input.client);
        return new NoResult();
    }

    public record Input(Client client) {
    }
}
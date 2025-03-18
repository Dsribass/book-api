package com.example.domain.usecase.client;

import com.example.domain.entity.Client;
import com.example.domain.exception.ItemNotExistsException;
import com.example.domain.gateway.ClientGateway;
import com.example.domain.utils.Logger;
import com.example.domain.utils.NoResult;
import com.example.domain.utils.UseCase;

public class UpdateClientUseCase extends UseCase<UpdateClientUseCase.Input, NoResult> {
    private final ClientGateway clientGateway;

    public UpdateClientUseCase(ClientGateway clientGateway, Logger logger) {
        super(logger);
        logger.setClazz(UpdateClientUseCase.class);
        this.clientGateway = clientGateway;
    }

    @Override
    protected NoResult run(UpdateClientUseCase.Input input) {
        if (clientGateway.findById(input.client.getId()).isEmpty()) {
            throw new ItemNotExistsException("Client with ID " + input.client.getId() + " does not exist");
        }

        clientGateway.update(input.client);
        return new NoResult();
    }

    public record Input(Client client) {
    }
}
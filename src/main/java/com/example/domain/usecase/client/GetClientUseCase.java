package com.example.domain.usecase.client;

import com.example.domain.entity.Client;
import com.example.domain.exception.ItemNotExistsException;
import com.example.domain.gateway.ClientGateway;
import com.example.domain.utils.Logger;
import com.example.domain.utils.UseCase;

public class GetClientUseCase extends UseCase<GetClientUseCase.Input, Client> {
    private final ClientGateway clientGateway;

    public GetClientUseCase(ClientGateway clientGateway, Logger logger) {
        super(logger);
        logger.setClazz(GetClientUseCase.class);
        this.clientGateway = clientGateway;
    }

    @Override
    protected Client run(Input input) {
        return clientGateway.findById(input.id)
                .orElseThrow(() -> new ItemNotExistsException("Book not found"));
    }

    public record Input(String id) {
    }
}

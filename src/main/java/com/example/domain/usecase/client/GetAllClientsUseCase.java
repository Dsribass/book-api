package com.example.domain.usecase.client;

import com.example.domain.entity.Client;
import com.example.domain.gateway.ClientGateway;
import com.example.domain.utils.Logger;
import com.example.domain.utils.UseCase;

import java.util.List;

public class GetAllClientsUseCase extends UseCase<GetAllClientsUseCase.Input, List<Client>> {
    private final ClientGateway clientGateway;

    public GetAllClientsUseCase(ClientGateway clientGateway, Logger logger) {
        super(logger);
        logger.setClazz(GetAllClientsUseCase.class);
        this.clientGateway = clientGateway;
    }

    @Override
    protected List<Client> run(GetAllClientsUseCase.Input input) {
        return clientGateway.findAll();
    }

    public record Input() {
    }
}

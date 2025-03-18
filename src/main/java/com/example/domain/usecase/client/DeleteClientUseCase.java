package com.example.domain.usecase.client;

import com.example.domain.gateway.ClientGateway;
import com.example.domain.utils.Logger;
import com.example.domain.utils.NoResult;
import com.example.domain.utils.UseCase;

public class DeleteClientUseCase extends UseCase<DeleteClientUseCase.Input, NoResult> {
    private final ClientGateway clientGateway;

    public DeleteClientUseCase(Logger logger, ClientGateway clientGateway) {
        super(logger);
        logger.setClazz(DeleteClientUseCase.class);
        this.clientGateway = clientGateway;
    }

    @Override
    protected NoResult run(Input input) {
        clientGateway.deleteById(input.id);
        return new NoResult();
    }

    public record Input(String id) {
    }
}

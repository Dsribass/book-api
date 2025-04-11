package com.example.infrastructure.controller;

import com.example.domain.usecase.client.*;
import com.example.infrastructure.controller.dto.client.ClientRequest;
import com.example.infrastructure.controller.dto.client.ClientResponse;
import com.example.infrastructure.controller.utils.DefaultResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/clients")
public class ClientController {
    private final AddClientUseCase addClientUseCase;
    private final GetClientUseCase getClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;
    private final GetAllClientsUseCase getAllClientsUseCase;

    public ClientController(AddClientUseCase addClientUseCase,
                            GetClientUseCase getClientUseCase,
                            UpdateClientUseCase updateClientUseCase,
                            DeleteClientUseCase deleteClientUseCase,
                            GetAllClientsUseCase getAllClientsUseCase
    ) {
        this.addClientUseCase = addClientUseCase;
        this.getClientUseCase = getClientUseCase;
        this.updateClientUseCase = updateClientUseCase;
        this.deleteClientUseCase = deleteClientUseCase;
        this.getAllClientsUseCase = getAllClientsUseCase;
    }

    @PostMapping
    ResponseEntity<DefaultResponse> save(@RequestBody @Valid ClientRequest client) {
        addClientUseCase.execute(new AddClientUseCase.Input(client.toEntity()));

        return new DefaultResponse("Client added successfully", null)
                .toResponseEntity(HttpStatus.CREATED);

    }

    @PostMapping("/{id}")
    ResponseEntity<DefaultResponse> update(
            @PathVariable String id,
            @RequestBody @Valid ClientRequest client
    ) {
        updateClientUseCase.execute(new UpdateClientUseCase.Input(client.toEntity(id)));
        return new DefaultResponse("Client updated successfully", null)
                .toResponseEntity();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<DefaultResponse> delete(@PathVariable String id) {
        deleteClientUseCase.execute(new DeleteClientUseCase.Input(id));
        return new DefaultResponse(
                "Client deleted successfully",
                Map.of("deletedId", id)
        )
                .toResponseEntity();
    }

    @GetMapping("/{id}")
    ResponseEntity<DefaultResponse> get(@PathVariable String id) {
        final var client = getClientUseCase.execute(new GetClientUseCase.Input(id));
        return new DefaultResponse(
                "Client retrieved successfully",
                ClientResponse.fromEntity(client)
        )
                .toResponseEntity();
    }

    @GetMapping
    ResponseEntity<DefaultResponse> getAll() {
        final var clients = getAllClientsUseCase.execute(new GetAllClientsUseCase.Input());
        return new DefaultResponse(
                "Clients retrieved successfully",
                clients.stream()
                        .map(ClientResponse::fromEntity)
                        .collect(Collectors.toList())
        )
                .toResponseEntity();
    }
}

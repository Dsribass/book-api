package com.example.infrastructure.controller;

import com.example.domain.usecase.client.*;
import com.example.infrastructure.controller.dto.ClientDTO;
import com.example.infrastructure.controller.response.DefaultResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    ResponseEntity<DefaultResponse> save(@RequestBody @Valid ClientDTO client) {
        addClientUseCase.execute(new AddClientUseCase.Input(client.toDomain()));

        return new DefaultResponse("Client added successfully", null)
                .toResponseEntity(HttpStatus.CREATED);

    }

    @PostMapping("/{id}")
    ResponseEntity<DefaultResponse> update(
            @PathVariable String id,
            @RequestBody @Valid ClientDTO client
    ) {
        updateClientUseCase.execute(new UpdateClientUseCase.Input(client.toDomain(id)));
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
        return new DefaultResponse(
                "Client retrieved successfully",
                getClientUseCase.execute(new GetClientUseCase.Input(id))
        )
                .toResponseEntity();
    }

    @GetMapping
    ResponseEntity<DefaultResponse> getAll() {
        return new DefaultResponse(
                "Clients retrieved successfully",
                getAllClientsUseCase.execute(new GetAllClientsUseCase.Input())
        )
                .toResponseEntity();
    }
}

package com.example.api.response;

import java.util.UUID;

public class CreateOrderResponse {

    private UUID id;

    public CreateOrderResponse(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}

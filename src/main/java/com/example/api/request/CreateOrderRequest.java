package com.example.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.domain.Product;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CreateOrderRequest {

    @Valid
    @NotNull private Product product;

    @JsonCreator
    public CreateOrderRequest(@JsonProperty("product") @NotNull final Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}

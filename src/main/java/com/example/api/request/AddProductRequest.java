package com.example.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.domain.Product;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class AddProductRequest {

    @Valid
    @NotNull private Product product;

    @JsonCreator
    public AddProductRequest(@JsonProperty("product") final Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}

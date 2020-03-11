package com.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Product {
    private final String id;
    private final BigDecimal price;
    @NotNull
    private final String name;

    @JsonCreator
    public Product(@JsonProperty("id") final String id, @JsonProperty("price") final BigDecimal price, @JsonProperty("name") final String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return Objects.equals(id, product.id) && Objects.equals(price, product.price) && Objects.equals(name, product.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, price, name);
//    }
}

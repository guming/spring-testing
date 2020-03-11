package com.example.domain.entity;

import com.example.domain.Product;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="order_item_entity")
public class OrderItem {
    @Id
    private String productId;
    private BigDecimal price;

    public OrderItem(final Product product) {
        this.productId = product.getId();
        this.price = product.getPrice();
    }

    public String getProductId() {
        return productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private OrderItem() {
    }

}

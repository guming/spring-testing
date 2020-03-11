package com.example.domain.entity;

import com.example.common.DomainException;
import com.example.domain.OrderStatus;
import com.example.domain.Product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
@Entity
@Table(name="order_entity")
public class Order {
    @Id
    private UUID id;
    private OrderStatus status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;
    private BigDecimal price;

    public Order(final UUID id, final Product product) {
        this.id = id;
        this.orderItems = new ArrayList<>(Collections.singletonList(new OrderItem(product)));
        this.status = OrderStatus.CREATED;
        this.price = product.getPrice();
    }

    public void complete() {
        validateState();
        this.status = OrderStatus.COMPLETED;
    }

    public void addOrder(final Product product) {
        validateState();
        validateProduct(product);
        orderItems.add(new OrderItem(product));
        price = price.add(product.getPrice());
    }

//    public void setOrderItems(List<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//    }

    public void removeOrder(final String pid) {
        validateState();
        final OrderItem orderItem = getOrderItem(pid);
        orderItems.remove(orderItem);

        price = price.subtract(orderItem.getPrice());
    }

    private OrderItem getOrderItem(final String pid) {
        return orderItems.stream()
            .filter(orderItem -> orderItem.getProductId()
                .equals(pid))
            .findFirst()
            .orElseThrow(() -> new DomainException("Product with " + pid + " doesn't exist."));
    }

    private void validateState() {
        if (OrderStatus.COMPLETED.equals(status)) {
            throw new DomainException("The order is in completed state.");
        }
    }

    private void validateProduct(final Product product) {
        if (product == null) {
            throw new DomainException("The product cannot be null.");
        }
    }

    public UUID getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, orderItems, price, status);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (!(obj instanceof Order))
//            return false;
//        Order other = (Order) obj;
//        return Objects.equals(id, other.id) && Objects.equals(orderItems, other.orderItems) && Objects.equals(price, other.price) && status == other.status;
//    }

    public Order() {
    }
}

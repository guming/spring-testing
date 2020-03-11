package com.example.domain;

import com.example.common.DomainException;
import com.example.domain.entity.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertEquals;


class OrderUnitTest {

    @Test
    void shouldCompleteOrder_thenChangeStatus() {
        final Order order = OrderProvider.getCreatedOrder();

        order.complete();

        assertEquals(OrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    void shouldCompleteOrder_fail_thenThrowException() {
        final Order order = OrderProvider.getCompletedOrder();

        final Executable executable = () -> order.complete();
        Assertions.assertThrows(DomainException.class, executable);
    }

    @Test
    void shouldAddProduct_thenUpdatePrice() {
        final Order order = OrderProvider.getCreatedOrder();
        final int orderOriginalProductSize = order
          .getOrderItems()
          .size();
        final BigDecimal orderOriginalPrice = order.getPrice();
        final Product productToAdd = new Product(UUID.randomUUID().toString(), new BigDecimal("20"), "secondProduct");

        order.addOrder(productToAdd);

        assertEquals(orderOriginalProductSize + 1, order
          .getOrderItems()
          .size());
        assertEquals(orderOriginalPrice.add(productToAdd.getPrice()), order.getPrice());
    }

    @Test
    void shouldAddProduct_fail_withNull_thenThrowException() {
        final Order order = OrderProvider.getCreatedOrder();
        Product productToAdd = null;

        final Executable executable = () -> order.addOrder(productToAdd);
        Assertions.assertThrows(DomainException.class, executable);
    }


    @Test
    void shouldAddProduct_fail_withInvalidStatus_thenThrowException() {
        final Order order = OrderProvider.getCompletedOrder();
        final Product productToAdd = new Product(UUID.randomUUID().toString(), new BigDecimal("20"), "secondProduct");

        final Executable executable = () -> order.addOrder(productToAdd);

        Assertions.assertThrows(DomainException.class, executable);
    }

    @Test
    void shouldRemoveProduct_thenUpdatePrice() {
        final Order order = OrderProvider.getCreatedOrder();
        final String productId = order
          .getOrderItems()
          .get(0)
          .getProductId();
        
        order.removeOrder(productId);

        assertEquals(0, order
          .getOrderItems()
          .size());
        assertEquals(BigDecimal.ZERO, order.getPrice());
    }
    @Test
    void shouldRemoveProduct_fail_withInvalidPid_thenThrowException() {
        final Order order = OrderProvider.getCreatedOrder();
        final String productId = order
                .getOrderItems()
                .get(0)
                .getProductId();
        final String prductuId_wrong = "test";
        final Executable executable = () -> order.removeOrder(prductuId_wrong);
        Assertions.assertThrows(DomainException.class, executable);
    }
}
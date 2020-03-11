package com.example.application;


import com.example.domain.entity.Order;
import com.example.domain.Product;
import com.example.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DomainOrderService implements OrderService {

    private final OrderRepository orderRepository;

    public DomainOrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public UUID createOrder(final Product product) {
        final Order order = new Order(UUID.randomUUID(), product);
        Order orderDB=orderRepository.save(order);
        return orderDB.getId();
    }

    @Override
    public void addProduct(final UUID id, final Product product) {
        final Order order = getOrder(id);
        order.addOrder(product);

        orderRepository.save(order);
    }

    @Override
    public void completeOrder(final UUID id) {
        final Order order = getOrder(id);
        order.complete();

        orderRepository.save(order);
    }

    @Override
    public void deleteProduct(UUID id, final String productId) {
        final Order order = getOrder(id);
        order.removeOrder(productId);

        orderRepository.save(order);
    }

    private Order getOrder(UUID id) {
        return orderRepository
          .findById(id)
          .orElseThrow(() -> new RuntimeException("Order with given id doesn't exist"));
    }
}

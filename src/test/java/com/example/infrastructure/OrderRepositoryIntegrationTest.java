package com.example.infrastructure;

import com.example.domain.Product;
import com.example.domain.entity.Order;
import com.example.domain.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void shouldSaveAndFetchOrder() {
        //given
        UUID orderId = UUID.randomUUID();
        Product mobilePhone = new Product(UUID.randomUUID().toString(), BigDecimal.valueOf(200), "mobile");
        Product razor = new Product(UUID.randomUUID().toString(), BigDecimal.valueOf(50), "razor");
        Order order = new Order(orderId,razor);
        order.addOrder(mobilePhone);
        //when
        orderRepository.save(order);
        Optional<Order> orderDB=orderRepository.findById(orderId);
        Order o = orderDB.get();
        //then
        assertThat(o.getId(), is(orderId));
    }
}

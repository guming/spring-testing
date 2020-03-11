package com.example.common;

import com.example.common.util.BeanMapper;
import com.example.domain.entity.Order;
import com.example.domain.OrderStatus;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class BeanMapperTest {

    @Test
    public void should_map_objectAtoObjectB() {
        //given
        UUID id = UUID.randomUUID();
        BigDecimal price = BigDecimal.valueOf(100);
        Order order = new Order();
        order.setId(id);
        order.setPrice(price);
        order.setStatus(OrderStatus.COMPLETED);

        //when
        Order orderEntity = BeanMapper.instance().map(order, Order.class);


        //then
        assertThat(orderEntity.getId(), is(id));
        assertThat(orderEntity.getStatus(), is(OrderStatus.COMPLETED));
        assertThat(orderEntity.getPrice(), is(price));
    }
}
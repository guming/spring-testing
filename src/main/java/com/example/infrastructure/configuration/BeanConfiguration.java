package com.example.infrastructure.configuration;

import com.example.DomainLayerApplication;
import com.example.application.DomainOrderService;
import com.example.application.OrderService;
import com.example.domain.repository.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = DomainLayerApplication.class)
public class BeanConfiguration {

    @Bean
    OrderService orderService(final OrderRepository orderRepository) {
        return new DomainOrderService(orderRepository);
    }
}

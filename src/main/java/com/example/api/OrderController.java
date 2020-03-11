package com.example.api;

import com.example.api.request.AddProductRequest;
import com.example.api.request.CreateOrderRequest;
import com.example.api.response.CreateOrderResponse;
import com.example.application.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CreateOrderResponse createOrder(@Valid @RequestBody final CreateOrderRequest createOrderRequest) {
        UUID id = orderService.createOrder(createOrderRequest.getProduct());
        return new CreateOrderResponse(id);
    }

    @PostMapping(value = "/{id}/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    void addProduct(@PathVariable final UUID id, @Valid @RequestBody final AddProductRequest addProductRequest) {
        orderService.addProduct(id, addProductRequest.getProduct());
    }

    @DeleteMapping(value = "/{id}/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    void deleteProduct(@PathVariable final UUID id, @Valid @RequestParam final String productId) {
        orderService.deleteProduct(id, productId);
    }

    @PostMapping("/{id}/complete")
    void completeOrder(@PathVariable final UUID id) {
        orderService.completeOrder(id);
    }

}

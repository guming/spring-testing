package com.example.api;

import com.example.api.request.AddProductRequest;
import com.example.api.request.CreateOrderRequest;
import com.example.application.OrderService;
import com.example.domain.Product;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * mockito and RestAssured
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    OrderService orderService;

    @Before
    public void setUp() {
        orderService  = mock(OrderService.class);
        RestAssuredMockMvc.standaloneSetup(new OrderController(orderService));
    }

    @Test
    public void shouldReturn200WhenCreateOrder() {
        //given

        Product mobilePhone = new Product(UUID.randomUUID().toString(),
                BigDecimal.valueOf(200), "mobile");
        UUID orderId=UUID.randomUUID();
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(mobilePhone);

        when(orderService.createOrder(mobilePhone)).thenReturn(orderId);
        //when & then

        given().contentType(ContentType.JSON).body(createOrderRequest)
                .when().post("/orders")
                .then().statusCode(200)
                .body("id",equalTo(orderId.toString()));

    }

    @Test
    public void shouldReturn400WhenCreateOrderRequestIsInvalid() {
        //given

        Product mobilePhone = new Product(null,BigDecimal.valueOf(200),null);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(mobilePhone);

        //when & then

        given().contentType(ContentType.JSON).body(createOrderRequest)
                .when().post("/orders/")
                .then().statusCode(400);

    }

    @Test
    public void shouldReturn200WhenAddProduct() {
        //given

        Product toy = new Product(UUID.randomUUID().toString(), BigDecimal.valueOf(100), "toy");
        UUID orderId=UUID.randomUUID();
        AddProductRequest addProductRequest = new AddProductRequest(toy);

        //when & then

        given().contentType(ContentType.JSON).body(addProductRequest)
                .post("/orders/{id}/products", orderId)
                .then().statusCode(200);

    }


    @Test
    public void shouldReturn400WhenAddProductRequestIsInvalid() {
        //given

        Product mobilePhone = new Product(null,BigDecimal.valueOf(200),null);
        UUID orderId=UUID.randomUUID();
        AddProductRequest addProductRequest = new AddProductRequest(mobilePhone);
        //when & then

        given().contentType(ContentType.JSON).body(addProductRequest)
                .post("/orders/{id}/products", orderId)
                .then().statusCode(400);

    }


    @Test
    public void shouldReturn200WhenDeleteProduct() {
        //given

        UUID productId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        //when & then

        given().contentType(ContentType.JSON)
                .formParam("productId",productId)
                .delete("/orders/{id}/products", orderId)
                .then().statusCode(200);
    }


    @Test
    public void shouldReturn400WhenDeleteProductRequestIsInvalid() {
        //given

        UUID productId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        //when & then

        given().contentType(ContentType.JSON)
                .delete("/orders/{id}/products", orderId)
                .then().statusCode(400);
    }


    @Test
    public void shouldReturn200WhenCompleteOrder() {
        //given

        UUID productId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        //when & then

        given().contentType(ContentType.JSON)
                .post("/orders/{id}/complete", orderId)
                .then().statusCode(200);
    }

}

package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderDTO orderDTO)
    {
        log.info("Placing order");
        return CompletableFuture.supplyAsync(() -> this.orderService.placeOrder(orderDTO));
    }


    public CompletableFuture<String> fallbackMethod(OrderDTO orderDTO, RuntimeException runtimeException)
    {
        log.info("Cannot Place Order Executing Fallback logic");
        return CompletableFuture.supplyAsync(
                () -> "Oops! Something went wrong. Please place an order later.");
    }
}

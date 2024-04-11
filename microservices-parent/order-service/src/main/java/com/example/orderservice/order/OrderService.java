package com.example.orderservice.order;

import com.example.orderservice.orderlineitems.OrderLineItems;
import com.example.orderservice.orderlineitems.OrderLineItemsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    public void placeOrder(OrderDTO orderDTO)
    {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderDTO.getOrderLineItemsDTOList()
                .stream()
                .map(this::mapToDTO)
                .toList();

        order.setOrderLineItemsList(orderLineItemsList);

        this.orderRepository.save(order);
    }


    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO)
    {
        return OrderLineItems.builder()
                .price(orderLineItemsDTO.getPrice())
                .quantity(orderLineItemsDTO.getQuantity())
                .skuCode(orderLineItemsDTO.getSkuCode())
                .build();

    }

}

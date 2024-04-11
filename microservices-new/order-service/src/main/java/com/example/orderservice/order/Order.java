package com.example.orderservice.order;

import com.example.orderservice.orderlineitems.OrderLineItems;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="t_orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @OneToMany(cascade = CascadeType.ALL)               private List<OrderLineItems> orderLineItemsList;
    private String orderNumber;

}

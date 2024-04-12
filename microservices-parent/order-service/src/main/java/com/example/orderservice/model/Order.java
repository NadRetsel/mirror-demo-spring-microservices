package com.example.orderservice.model;

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

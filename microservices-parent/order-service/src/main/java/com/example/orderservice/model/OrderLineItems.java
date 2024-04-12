package com.example.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "t_order_line_items")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderLineItems {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String orderNumber;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}

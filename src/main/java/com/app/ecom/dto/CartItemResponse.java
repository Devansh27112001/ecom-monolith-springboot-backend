package com.app.ecom.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {

    private BigDecimal totalPrice;
    private String productName;
    private Integer quantity;
}

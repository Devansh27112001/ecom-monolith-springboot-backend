package com.app.ecom.service;

import com.app.ecom.dao.OrderRepo;
import com.app.ecom.dto.OrderResponse;
import com.app.ecom.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartService cartService;

    public OrderResponse createOrder(String userId) {
        // Validate the cart items
        List<CartItem> items = cartService.findCartItemsByUserId(userId);
        // Validate for user
        // Calculate total price
        // Create order
        // Clear the cart
        return null;
    }
}

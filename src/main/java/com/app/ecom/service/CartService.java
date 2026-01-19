package com.app.ecom.service;

import com.app.ecom.dao.CartRepo;
import com.app.ecom.dao.ProductRepo;
import com.app.ecom.dao.UserRepo;
import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.model.CartItem;
import com.app.ecom.model.Product;
import com.app.ecom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    public boolean addToCart(String id, CartItemRequest request) {
        // Look for product
        Optional<Product> productOptional = productRepo.findById(request.getProductId());
        if(productOptional.isEmpty()){
            return false;
        }

        Product product = productOptional.get();
        if(product.getStockQuantity() < request.getQuantity()){
            return false;
        }

        // Look for user
        Optional<User> userOpt = userRepo.findById(Long.valueOf(id));
        if(userOpt.isEmpty()){
            return false;
        }

        User user = userOpt.get();

        CartItem existingCartItem = cartRepo.findByUserAndProduct(user,product);
        if(existingCartItem != null){
            // Update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartRepo.save(existingCartItem);
        }else{
            // Create new CartItem
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartRepo.save(cartItem);
        }

        return true;
    }
}

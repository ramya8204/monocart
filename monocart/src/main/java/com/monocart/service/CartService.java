package com.monocart.service;


import com.monocart.entity.Cart;

import java.util.List;

public interface CartService {

    Cart addToCart(Long userId, Long productId, int quantity);
    void removeFromCart(Long userId, Long productId);
    List<Cart> getCartItems(Long userId);
    double calculateTotal(Long userId);
}

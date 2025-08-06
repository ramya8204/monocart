package com.monocart.controller;

import com.monocart.entity.Cart;
import com.monocart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public Cart addToCart(@RequestParam Long userId,
                          @RequestParam Long productId,
                          @RequestParam int quantity) {
        return cartService.addToCart(userId, productId, quantity);
    }

    @DeleteMapping("/remove")
    public String removeFromCart(@RequestParam Long userId,
                                 @RequestParam Long productId) {
        cartService.removeFromCart(userId, productId);
        return "Product removed from cart";
    }

    @GetMapping("/{userId}")
    public List<Cart> viewCart(@PathVariable Long userId) {
        return cartService.getCartItems(userId);
    }

    @GetMapping("/total/{userId}")
    public double getTotal(@PathVariable Long userId) {
        return cartService.calculateTotal(userId);
    }
}


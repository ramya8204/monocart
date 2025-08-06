package com.monocart.service;

import com.monocart.entity.Cart;
import com.monocart.entity.Product;
import com.monocart.repository.CartRepository;
import com.monocart.repository.ProductRepository;
import com.monocart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart addToCart(Long userId, Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null || product.getStock() < quantity) {
            return null; // product doesn't exist or insufficient stock
        }

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setPrice(product.getPrice() * quantity);

        return cartRepository.save(cart);
    }

    @Override
    public void removeFromCart(Long userId, Long productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }

    @Override
    public List<Cart> getCartItems(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public double calculateTotal(Long userId) {
        List<Cart> cartItems = cartRepository.findByUserId(userId);
        return cartItems.stream().mapToDouble(Cart::getPrice).sum();
    }
}

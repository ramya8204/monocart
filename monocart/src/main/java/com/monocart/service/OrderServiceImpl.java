package com.monocart.service;

import com.monocart.dto.OrderDTO;
import com.monocart.entity.Order;
import com.monocart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDTO createOrder(OrderDTO dto) {
        Order order = new Order(null, dto.getUserId(), dto.getOrderDate(), dto.getTotalAmount(), dto.getStatus());
        Order saved = orderRepository.save(order);
        return new OrderDTO(saved.getId(), saved.getUserId(), saved.getOrderDate(), saved.getTotalAmount(), saved.getStatus());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderDTO(order.getId(), order.getUserId(), order.getOrderDate(), order.getTotalAmount(), order.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return new OrderDTO(order.getId(), order.getUserId(), order.getOrderDate(), order.getTotalAmount(), order.getStatus());
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO dto) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setUserId(dto.getUserId());
        order.setOrderDate(dto.getOrderDate());
        order.setTotalAmount(dto.getTotalAmount());
        order.setStatus(dto.getStatus());
        Order updated = orderRepository.save(order);
        return new OrderDTO(updated.getId(), updated.getUserId(), updated.getOrderDate(), updated.getTotalAmount(), updated.getStatus());
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
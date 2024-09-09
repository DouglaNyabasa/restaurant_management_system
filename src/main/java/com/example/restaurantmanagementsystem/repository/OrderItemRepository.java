package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}

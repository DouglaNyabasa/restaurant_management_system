package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {


}

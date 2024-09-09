package com.example.restaurantmanagementsystem.service;

import com.example.restaurantmanagementsystem.model.Order;
import com.example.restaurantmanagementsystem.model.User;
import com.example.restaurantmanagementsystem.request.OrderRequest;

import java.util.List;

public interface OrderService {

    public Order findOrderById(Long orderId)throws Exception;
    public List<Order> getRestaurantOrder(Long restaurantId,String orderStatus) throws Exception;
    public void cancelOrder(Long orderId) throws Exception;
    public Order updateOrder(Long orderId,String orderStatus)throws Exception;
    public Order createOrder(OrderRequest orderRequest, User user) throws Exception;
    public List<Order> getUsersOrders(Long userId) throws Exception;
}

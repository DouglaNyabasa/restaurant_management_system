package com.example.restaurantmanagementsystem.service;

import com.example.restaurantmanagementsystem.model.Cart;
import com.example.restaurantmanagementsystem.model.CartItem;
import com.example.restaurantmanagementsystem.request.AddCartItemRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CartService  {

    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt)throws Exception;

    public Long calculateCartTotals (Cart cart)throws Exception;

    public Cart findCartById(Long id)throws Exception;
    public Cart findCartByUserId (Long userId) throws Exception;
    public Cart clearCart(Long userId) throws Exception;
}

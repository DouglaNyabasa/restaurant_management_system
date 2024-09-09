package com.example.restaurantmanagementsystem.request;

import com.example.restaurantmanagementsystem.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}

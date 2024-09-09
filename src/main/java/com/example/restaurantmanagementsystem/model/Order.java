package com.example.restaurantmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToOne
    @JsonIgnore
    private  Restaurant restaurant;

    @OneToMany
    private List<OrderItem> items;
    private int totalItem;

//    private Payment payment;

    @ManyToOne
    private Address deliveryAddress;
    private Date createdAt;
    private String orderStatus;
    private  Long totalAmount;


}

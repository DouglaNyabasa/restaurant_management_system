package com.example.restaurantmanagementsystem.resources;

import com.example.restaurantmanagementsystem.model.CartItem;
import com.example.restaurantmanagementsystem.model.Order;
import com.example.restaurantmanagementsystem.model.User;
import com.example.restaurantmanagementsystem.request.AddCartItemRequest;
import com.example.restaurantmanagementsystem.request.OrderRequest;
import com.example.restaurantmanagementsystem.service.OrderService;
import com.example.restaurantmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request, @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(request,user);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getAllOrders( @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List <Order> order = orderService.getUsersOrders(user.getId());

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}

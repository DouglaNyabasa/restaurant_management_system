package com.example.restaurantmanagementsystem.resources;

import com.example.restaurantmanagementsystem.model.Order;
import com.example.restaurantmanagementsystem.model.User;
import com.example.restaurantmanagementsystem.request.OrderRequest;
import com.example.restaurantmanagementsystem.service.OrderService;
import com.example.restaurantmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @PutMapping("/order/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable String orderStatus, @PathVariable Long id,@RequestParam(required = false)String order_status, @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
       Order order = orderService.updateOrder(id,order_status);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable Long id,@RequestParam(required = false)String order_status, @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List <Order> order = orderService.getRestaurantOrder(id, order_status);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}

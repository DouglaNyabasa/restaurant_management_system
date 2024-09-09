package com.example.restaurantmanagementsystem.resources;

import com.example.restaurantmanagementsystem.model.Food;
import com.example.restaurantmanagementsystem.model.Restaurant;
import com.example.restaurantmanagementsystem.model.User;
import com.example.restaurantmanagementsystem.response.CreateFoodRequest;
import com.example.restaurantmanagementsystem.response.MessageResponse;
import com.example.restaurantmanagementsystem.service.FoodService;
import com.example.restaurantmanagementsystem.service.RestaurantService;
import com.example.restaurantmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @PostMapping()
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req, @RequestHeader("Authorization") String jwt ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req, req.getCategory(),restaurant);

        return  new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id, @RequestHeader("Authorization") String jwt ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        foodService.deleteFood(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Food Successfully deleted");

        return  new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Food> updateFoodAvailability(@PathVariable Long id, @RequestHeader("Authorization") String jwt ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailabilityStatus(id);

        return  new ResponseEntity<>(food, HttpStatus.CREATED);
    }
}

package com.example.restaurantmanagementsystem.resources;

import com.example.restaurantmanagementsystem.model.Food;
import com.example.restaurantmanagementsystem.model.Restaurant;
import com.example.restaurantmanagementsystem.model.User;
import com.example.restaurantmanagementsystem.response.CreateFoodRequest;
import com.example.restaurantmanagementsystem.service.FoodService;
import com.example.restaurantmanagementsystem.service.RestaurantService;
import com.example.restaurantmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


//    @GetMapping("/search")
//    public ResponseEntity<List<Food>> searchFood(@RequestParam String name, @RequestHeader("Authorization") String jwt ) throws Exception {
//
//        User user = userService.findUserByJwtToken(jwt);
//
//        List<Food> foods = foodService.searchFood(name);
//
//        return  new ResponseEntity<>(foods, HttpStatus.CREATED);
//    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam(required = false) String food_category,@RequestParam boolean vegetarian,@RequestParam boolean seasional,@RequestParam boolean nonVeg,@PathVariable Long restaurantId, @RequestHeader("Authorization") String jwt ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.getRestaurantFood(restaurantId,vegetarian,nonVeg,seasional,food_category);

        return  new ResponseEntity<>(foods, HttpStatus.OK);
    }

}

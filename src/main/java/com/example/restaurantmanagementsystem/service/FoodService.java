package com.example.restaurantmanagementsystem.service;

import com.example.restaurantmanagementsystem.model.Category;
import com.example.restaurantmanagementsystem.model.Food;
import com.example.restaurantmanagementsystem.model.Restaurant;
import com.example.restaurantmanagementsystem.response.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req , Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;
    public List<Food> getRestaurantFood(Long restaurantId,boolean isVegetarian,boolean isNoneVeg, boolean isSeasonal,String foodCategory);

//    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long foodId) throws Exception;
    public Food updateAvailabilityStatus(Long foodId) throws  Exception;
}

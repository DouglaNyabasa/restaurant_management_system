package com.example.restaurantmanagementsystem.implemantation;

import com.example.restaurantmanagementsystem.model.Category;
import com.example.restaurantmanagementsystem.model.Food;
import com.example.restaurantmanagementsystem.model.Restaurant;
import com.example.restaurantmanagementsystem.repository.FoodRepository;
import com.example.restaurantmanagementsystem.response.CreateFoodRequest;
import com.example.restaurantmanagementsystem.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;
    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredients(req.getIngredients());
        food.setSessional(req.isSessional());
        food.setVegetarian(req.isVegetarian());


       Food savedFood =  foodRepository.save(food);
       restaurant.getFoods().add(savedFood);
       return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);

    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian, boolean isNoneVeg, boolean isSeasonal, String foodCategory) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if (isVegetarian){
            foods = filterByVegetarian(foods ,isVegetarian);
        }
        if (isNoneVeg){
            foods = filterByNonVeg(foods,isNoneVeg);
        }
        if (isSeasonal){
            foods = filterBySessional(foods,isSeasonal);
        }
        if (foodCategory!=null && !foodCategory.equals("")){
            foods = filterByCategory(foods,foodCategory);
        }
        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if (food.getCategory()!=null){
               return food.getCategory().getName().equals(foodCategory);
            }
            return  false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods, boolean isNoneVeg) {
        return foods.stream().filter(food -> food.isVegetarian()== false).collect(Collectors.toList());
    }

    private List<Food> filterBySessional(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSessional()== isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian()== isVegetarian).collect(Collectors.toList());
    }

//    @Override
//    public List<Food> searchFood(String keyword) {
//        return foodRepository.searchFood(keyword);
//    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if (optionalFood.isEmpty()){
            throw new Exception("Food Does Not Exist....");
        }

        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(food.isAvailable());
        return foodRepository.save(food);

    }
}

package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.model.IngredientCategory;
import com.example.restaurantmanagementsystem.model.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {

    List<IngredientCategory> findByRestaurantId(Long id);




}

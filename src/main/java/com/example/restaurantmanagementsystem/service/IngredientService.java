package com.example.restaurantmanagementsystem.service;

import com.example.restaurantmanagementsystem.model.IngredientCategory;
import com.example.restaurantmanagementsystem.model.IngredientsItem;

import java.util.List;

public interface IngredientService {

    public IngredientCategory createIngredientCategory(String name,Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception;
    public List<IngredientsItem> findRestaurantIngredient(Long restaurantId);

    public IngredientsItem updateStock(Long id) throws Exception;
}

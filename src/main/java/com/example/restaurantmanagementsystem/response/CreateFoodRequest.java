package com.example.restaurantmanagementsystem.response;

import com.example.restaurantmanagementsystem.model.Category;
import com.example.restaurantmanagementsystem.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private  String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long restaurantId;
    private boolean vegetarian;
    private boolean sessional;
    private List<IngredientsItem> ingredients;

}

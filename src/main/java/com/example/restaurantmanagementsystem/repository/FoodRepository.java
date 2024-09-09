package com.example.restaurantmanagementsystem.repository;

import com.example.restaurantmanagementsystem.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {

    List<Food> findByRestaurantId(Long restaurantId);

//    @Query(value = "SELECT * FROM food f WHERE f.name LIKE %:keyword% OR f.food_category_id IN (SELECT id FROM food_category WHERE name LIKE %:keyword%)", nativeQuery = true)
//    List<Food> searchFood(@Param("keyword") String keyword);
//    @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.foodCategory.name LIKE %:keyword%")
//    List<Food> searchFood(@Param("keyword") String keyword);
}

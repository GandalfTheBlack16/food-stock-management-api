package com.gandalftheblack.foodstock.domain.repositories;

import com.gandalftheblack.foodstock.domain.entities.Food;

import java.util.List;
import java.util.UUID;

public interface FoodRepository {

    List<Food> getFoodList();

    Food addFood(Food food);

    Food editFoodById(UUID foodId, Food food);

    Food removeFoodById(UUID foodId);
}

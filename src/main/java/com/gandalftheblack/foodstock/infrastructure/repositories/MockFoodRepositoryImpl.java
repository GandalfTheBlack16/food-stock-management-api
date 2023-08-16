package com.gandalftheblack.foodstock.infrastructure.repositories;

import com.gandalftheblack.foodstock.domain.entities.Food;
import com.gandalftheblack.foodstock.domain.entities.valueobjects.FoodName;
import com.gandalftheblack.foodstock.domain.entities.valueobjects.FoodQuantity;
import com.gandalftheblack.foodstock.domain.repositories.FoodRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Profile("mock_repository")
public class MockFoodRepositoryImpl implements FoodRepository {

    private final List<Food> foodList = new ArrayList<>();
    @Override
    public List<Food> getFoodList() {
        return foodList;
    }

    @Override
    public Food addFood(Food food) {
        food.setId(UUID.randomUUID());
        foodList.add(food);
        return food;
    }

    @Override
    public Food editFoodById(UUID foodId, Food food) {
        Food foundFood = foodList.stream()
                .filter(food1 -> food1.getId().equals(foodId))
                .findAny()
                .orElse(null);
        if (foundFood != null) {
            int index = foodList.indexOf(foundFood);
            foundFood.setName(new FoodName(food.getName().getValue()));
            foundFood.setQuantity(new FoodQuantity(food.getQuantity().getValue()));
            foodList.set(index, foundFood);
        }
        return foundFood;
    }

    @Override
    public Food removeFoodById(UUID foodId) {
        Food foundFood = foodList.stream()
                .filter(food1 -> food1.getId().equals(foodId))
                .findAny()
                .orElse(null);
        if (foundFood != null) {
            foodList.remove(foundFood);
        }
        return foundFood;
    }
}

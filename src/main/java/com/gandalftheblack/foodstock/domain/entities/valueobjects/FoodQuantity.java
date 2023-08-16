package com.gandalftheblack.foodstock.domain.entities.valueobjects;

import com.gandalftheblack.foodstock.domain.exceptions.InvalidFoodQuantityException;
import lombok.Data;

@Data
public class FoodQuantity {
    private Integer value;

    public FoodQuantity(Integer value) {
        if(value < 1) {
            throw new InvalidFoodQuantityException("Food quantity must be 1 at least");
        }
        this.value = value;
    }
}

package com.gandalftheblack.foodstock.domain.entities.valueobjects;

import com.gandalftheblack.foodstock.domain.exceptions.InvalidFoodNameException;
import lombok.Data;

@Data
public class FoodName {
    private String value;

    public FoodName(String value) throws InvalidFoodNameException {
        if (value.isBlank()){
            throw new InvalidFoodNameException("Food name must be defined");
        }
        if (value.length() < 3 || value.length() > 48) {
            throw new InvalidFoodNameException("Food name must have between 3 and 48 characters");
        }
        this.value = value;
    }
}

package com.gandalftheblack.foodstock.domain.entities.valueobjects;

import com.gandalftheblack.foodstock.domain.exceptions.InvalidFoodNameException;
import lombok.Data;

@Data
public class FoodName {
    private String name;

    public FoodName(String name) throws InvalidFoodNameException {
        if (name.isBlank()){
            throw new InvalidFoodNameException("Food name must be defined");
        }
        if (name.length() < 3 || name.length() > 48) {
            throw new InvalidFoodNameException("Food name must have between 3 and 48 characters");
        }
        this.name = name;
    }
}

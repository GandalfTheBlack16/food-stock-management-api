package com.gandalftheblack.foodstock.domain.entities;

import com.gandalftheblack.foodstock.domain.entities.annotations.Default;
import com.gandalftheblack.foodstock.domain.entities.valueobjects.FoodName;
import com.gandalftheblack.foodstock.domain.entities.valueobjects.FoodQuantity;
import com.gandalftheblack.foodstock.domain.exceptions.InvalidFoodNameException;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Food {

    private UUID id;
    private FoodName name;
    private FoodQuantity quantity;
    private Date createdAt;
    private Date modifiedAt;

    @Default
    public Food(UUID id, FoodName name, FoodQuantity quantity, Date createdAt, Date modifiedAt) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Food(String name, Integer quantity) throws InvalidFoodNameException {
        this.id = UUID.randomUUID();
        this.name = new FoodName(name);
        this.quantity = new FoodQuantity(quantity);
        this.createdAt = new Date();
        this.modifiedAt = this.createdAt;
    }
}

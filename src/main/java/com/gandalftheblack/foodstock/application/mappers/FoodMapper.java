package com.gandalftheblack.foodstock.application.mappers;

import com.gandalftheblack.foodstock.application.dtos.FoodDto;
import com.gandalftheblack.foodstock.domain.entities.Food;
import com.gandalftheblack.foodstock.domain.entities.valueobjects.FoodName;
import com.gandalftheblack.foodstock.domain.entities.valueobjects.FoodQuantity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FoodMapper {

    List<FoodDto> foodListToDto(List<Food> foodList);

    FoodDto foodToDto(Food food);

    Food dtoToFood(FoodDto foodDto);

    static String foodIdMap (UUID id) {
        return id.toString();
    }

    static String foodNameMap(FoodName value) {
        return value.getName();
    }

    static Integer foodQuantityMap(FoodQuantity value) {
        return value.getQuantity();
    }
}

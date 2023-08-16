package com.gandalftheblack.foodstock.application.mappers;

import com.gandalftheblack.foodstock.application.dtos.FoodDto;
import com.gandalftheblack.foodstock.domain.entities.Food;
import com.gandalftheblack.foodstock.domain.entities.valueobjects.FoodName;
import com.gandalftheblack.foodstock.domain.entities.valueobjects.FoodQuantity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FoodMapper {

    public List<FoodDto> foodListToDto(List<Food> foodList) {
        if ( foodList == null ) {
            return null;
        }

        List<FoodDto> list = new ArrayList<>( foodList.size() );
        for ( Food food : foodList ) {
            list.add( foodToDto( food ) );
        }

        return list;
    }

    public FoodDto foodToDto(Food food) {
        return new FoodDto(
                food.getId().toString(),
                food.getName().getValue(),
                food.getQuantity().getValue(),
                food.getCreatedAt(),
                food.getModifiedAt()
        );
    }

    public Food dtoToFood(FoodDto foodDto) {
        return new Food(
                UUID.fromString(foodDto.getId()),
                new FoodName(foodDto.getName()),
                new FoodQuantity(foodDto.getQuantity()),
                foodDto.getCreatedAt(),
                foodDto.getModifiedAt()
        );
    }
}

package com.gandalftheblack.foodstock.application.mappers;

import com.gandalftheblack.foodstock.application.dtos.FoodDto;
import com.gandalftheblack.foodstock.domain.entities.Food;
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
        Food mapped = new Food(foodDto.getName(), foodDto.getQuantity());
        if (foodDto.getId() != null) {
            mapped.setId(UUID.fromString(foodDto.getId()));
        }
        if (foodDto.getCreatedAt() != null){
            mapped.setCreatedAt(foodDto.getCreatedAt());
        }
        if (foodDto.getModifiedAt() != null){
            mapped.setModifiedAt(foodDto.getModifiedAt());
        }
        return mapped;
    }
}

package com.gandalftheblack.foodstock.application.services;

import com.gandalftheblack.foodstock.application.dtos.FoodDto;
import com.gandalftheblack.foodstock.application.mappers.FoodMapper;
import com.gandalftheblack.foodstock.domain.repositories.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository repository;

    private final FoodMapper mapper;

    public List<FoodDto> getFoodList() {
        return mapper.foodListToDto(repository.getFoodList());
    }

    public FoodDto createFood(FoodDto foodDto) {
        return mapper.foodToDto(repository.addFood(mapper.dtoToFood(foodDto)));
    }

    public FoodDto editFood(String id, FoodDto foodDto) {
        return mapper.foodToDto(repository.editFoodById(UUID.fromString(id), mapper.dtoToFood(foodDto)));
    }

    public FoodDto removeFood(String id) {
        return mapper.foodToDto(repository.removeFoodById(UUID.fromString(id)));
    }
}

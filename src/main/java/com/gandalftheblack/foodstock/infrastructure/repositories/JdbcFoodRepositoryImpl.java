package com.gandalftheblack.foodstock.infrastructure.repositories;

import com.gandalftheblack.foodstock.domain.entities.Food;
import com.gandalftheblack.foodstock.domain.entities.valueobjects.FoodName;
import com.gandalftheblack.foodstock.domain.entities.valueobjects.FoodQuantity;
import com.gandalftheblack.foodstock.domain.repositories.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Profile("jdbc_repository")
@RequiredArgsConstructor
public class JdbcFoodRepositoryImpl implements FoodRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Food> getFoodList() {
        return jdbcTemplate.query("SELECT * FROM FOOD", rs -> {
            List<Food> mapRet = new ArrayList<>();
            while (rs.next()) {
                mapRet.add(new Food(
                                UUID.fromString(rs.getString("id")),
                                new FoodName(rs.getString("name")),
                                new FoodQuantity(rs.getInt("quantity")),
                                rs.getDate("created_at"),
                                rs.getDate("modified_at")
                        )
                );
            }
            return mapRet;
        });
    }

    @Override
    public Food addFood(Food food) {
        int rowsAffected = jdbcTemplate.update("INSERT INTO FOOD(ID, NAME, QUANTITY, CREATED_AT, MODIFIED_AT) VALUES (?,?,?,?,?)",
                food.getId(),
                food.getName().getValue(),
                food.getQuantity().getValue(),
                food.getCreatedAt(),
                food.getModifiedAt()
        );
        if (rowsAffected == 0) {
            throw new RuntimeException("Cannot perform insert");
        }
        return food;
    }

    @Override
    public Food editFoodById(UUID foodId, Food food) {
        return null;
    }

    @Override
    public Food removeFoodById(UUID foodId) {
        return null;
    }
}

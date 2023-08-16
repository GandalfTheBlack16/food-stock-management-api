package com.gandalftheblack.foodstock.infrastructure.repositories.jdbc;

import com.gandalftheblack.foodstock.domain.entities.Food;
import com.gandalftheblack.foodstock.domain.entities.valueobjects.FoodName;
import com.gandalftheblack.foodstock.domain.entities.valueobjects.FoodQuantity;
import com.gandalftheblack.foodstock.domain.repositories.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
@Profile("jdbc_repository")
@RequiredArgsConstructor
public class JdbcFoodRepositoryImpl implements FoodRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Food> getFoodList() {
        return jdbcTemplate.query(SqlConstants.GET_FOOD_LIST_QUERY, rs -> {
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
        int rowsAffected = jdbcTemplate.update(SqlConstants.ADD_FOOD_QUERY,
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
        Date modified = new Date();
        int rowsAffected = jdbcTemplate.update(SqlConstants.EDIT_FOOD_QUERY,
                food.getName().getValue(),
                food.getQuantity().getValue(),
                modified,
                foodId
        );
        if (rowsAffected == 0) {
            throw new RuntimeException("Cannot perform update");
        }
        food.setModifiedAt(modified);
        return food;
    }

    @Override
    public Food removeFoodById(UUID foodId) {
        List<Food> results = jdbcTemplate.query(SqlConstants.FIND_FOOD_BY_ID, new Object[] { foodId }, rs -> {
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

        assert results != null;
        if (results.isEmpty()) {
            return null;
        }
        int rowsAffected = jdbcTemplate.update(SqlConstants.DELETE_FOOD_QUERY, foodId);
        if (rowsAffected == 0) {
            throw new RuntimeException("Cannot perform update");
        }
        return results.get(0);
    }
}

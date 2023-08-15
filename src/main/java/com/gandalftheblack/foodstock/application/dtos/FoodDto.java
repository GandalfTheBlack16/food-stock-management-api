package com.gandalftheblack.foodstock.application.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class FoodDto {

    private String id;
    private String name;
    private Integer quantity;
    private Date createdAt;
    private Date modifiedAt;
}

package com.gandalftheblack.foodstock.infrastructure.entities;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponseEntity {

    private Integer status;
    private String error;
    private Date timestamp = new Date();

    public ErrorResponseEntity(Integer status, String error) {
        this.status = status;
        this.error = error;
    }
}

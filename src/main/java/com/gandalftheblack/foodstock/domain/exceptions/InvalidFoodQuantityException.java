package com.gandalftheblack.foodstock.domain.exceptions;

public class InvalidFoodQuantityException extends RuntimeException{

    public InvalidFoodQuantityException(String message) {
        super(message);
    }
}

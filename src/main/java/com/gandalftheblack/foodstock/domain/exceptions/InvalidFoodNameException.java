package com.gandalftheblack.foodstock.domain.exceptions;

public class InvalidFoodNameException extends RuntimeException{

    public InvalidFoodNameException(String message) {
        super(message);
    }
}

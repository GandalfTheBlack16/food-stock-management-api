package com.gandalftheblack.foodstock.infrastructure.controllers;

import com.gandalftheblack.foodstock.application.dtos.FoodDto;
import com.gandalftheblack.foodstock.application.services.FoodService;
import com.gandalftheblack.foodstock.domain.exceptions.InvalidFoodNameException;
import com.gandalftheblack.foodstock.domain.exceptions.InvalidFoodQuantityException;
import com.gandalftheblack.foodstock.infrastructure.entities.ErrorResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService service;

    @GetMapping("/api/foods")
    public ResponseEntity<?> getFoodList() {
        try {
            return new ResponseEntity<>(service.getFoodList(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/foods")
    public ResponseEntity<?> postFood(@RequestBody FoodDto foodDto) {
        try {
            return new ResponseEntity<>(service.createFood(foodDto), HttpStatus.CREATED);
        }catch (InvalidFoodNameException | InvalidFoodQuantityException e){
            return new ResponseEntity<>(new ErrorResponseEntity(400, e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/foods/{id}")
    public ResponseEntity<?> putFood(@PathVariable String id, @RequestBody FoodDto foodDto) {
        try {
            FoodDto body = service.editFood(id, foodDto);
            if (body == null) {
                return new ResponseEntity<>(new ErrorResponseEntity(404, "Food does not exists"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (InvalidFoodNameException | InvalidFoodQuantityException e) {
            return new ResponseEntity<>(new ErrorResponseEntity(400, e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/foods/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable String id) {
        try {
            FoodDto body = service.removeFood(id);
            if (body == null) {
                return new ResponseEntity<>(new ErrorResponseEntity(404, "Food does not exists"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.cars.mscars.Controller;

import com.cars.mscars.DTO.CarsDTORequest;
import com.cars.mscars.DTO.CarsDTOResponse;
import com.cars.mscars.Service.CarsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/cars")
public class CarsController {

    @Autowired
    private CarsService carsService;

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<CarsDTOResponse> findById(@PathVariable String id) {
        return new ResponseEntity<>(carsService.findCarById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/get/cars")
    public ResponseEntity<List<CarsDTOResponse>> findAll() {
        return new ResponseEntity<>(carsService.findAllCars(), HttpStatus.OK);
    }

    @PostMapping(value = "/post")
    public ResponseEntity<CarsDTOResponse> create(@Valid @RequestBody CarsDTORequest carsDTORequest) {
        return new ResponseEntity<>(carsService.createCar(carsDTORequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<CarsDTOResponse> update(@Valid @RequestBody CarsDTORequest carsDTORequest, @PathVariable String id) {
        return new ResponseEntity<>(carsService.updateCar(carsDTORequest, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        return new ResponseEntity<>(carsService.deleteCarById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/sortedCars")
    public ResponseEntity<List<CarsDTOResponse>> rafflingOffCars() {
        return new ResponseEntity<>(carsService.rafflingOffCars(), HttpStatus.OK);
    }
}

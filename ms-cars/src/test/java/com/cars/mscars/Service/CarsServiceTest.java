package com.cars.mscars.Service;

import com.cars.mscars.DTO.CarsDTORequest;
import com.cars.mscars.DTO.CarsDTOResponse;
import com.cars.mscars.Entity.Cars;
import com.cars.mscars.Repositoty.CarsRepository;
import com.cars.mscars.Service.Exceptions.AlreadyRegisteredException;
import com.cars.mscars.Service.Exceptions.CarNotFoundException;
import com.cars.mscars.Utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarsServiceTest {

    @InjectMocks
    private CarsService carsService;

    @Mock
    private CarsRepository carsRepository;

    @Spy
    private ModelMapper mapper;

    private static final String CARSDTO = "Payload/CarDTORequest.json";
    private static final String CARS = "Payload/Car.json";

    @Test
    void findCarById() throws IOException {
        Cars car = JsonUtils.getObjectFromFile(CARS, Cars.class);
        CarsDTOResponse carsDTOResponse = JsonUtils.getObjectFromFile(CARS, CarsDTOResponse.class);
        when(carsRepository.findById(anyString())).thenReturn(Optional.ofNullable(car));
        CarsDTOResponse response = carsService.findCarById(anyString());

        assertNotNull(response);
        assertEquals(carsDTOResponse, response);
    }

    @Test
    void FindCarById_ERROR_CarNotFoundException() {
        when(carsRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(CarNotFoundException.class, () -> carsService.findCarById(anyString()));
    }

    @Test
    void findAllCars() throws IOException {
        Cars cars = JsonUtils.getObjectFromFile(CARS, Cars.class);
        CarsDTOResponse classDTOResponse = JsonUtils.getObjectFromFile(CARS, CarsDTOResponse.class);

        when(carsRepository.findAll()).thenReturn(List.of(cars));
        List<CarsDTOResponse> response = carsService.findAllCars();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(classDTOResponse, response.get(0));
    }

    @Test
    void createCar() throws IOException {
        CarsDTORequest carsDTORequest = JsonUtils.getObjectFromFile(CARSDTO, CarsDTORequest.class);
        Cars cars = JsonUtils.getObjectFromFile(CARS, Cars.class);
        CarsDTOResponse carsDTOResponse = JsonUtils.getObjectFromFile(CARS, CarsDTOResponse.class);

        when(carsRepository.save(any())).thenReturn(cars);
        CarsDTOResponse response = carsService.createCar(carsDTORequest);

        assertNotNull(response);
        assertEquals(carsDTOResponse, response);
    }

    @Test
    void validateEqualsCarsAndPilot_CreateCar_ERROR_AlreadyRegisteredException() throws IOException {
        CarsDTORequest carsDTORequest = JsonUtils.getObjectFromFile(CARSDTO, CarsDTORequest.class);
        Cars car = JsonUtils.getObjectFromFile(CARS, Cars.class);

        when(carsRepository.findAll()).thenReturn(List.of(car));

        assertThrows(AlreadyRegisteredException.class, () -> carsService.createCar(carsDTORequest));
    }

    @Test
    void updateCar() throws IOException {
        String ID_car = "1";
        CarsDTORequest carsDTORequest = JsonUtils.getObjectFromFile(CARSDTO, CarsDTORequest.class);
        Cars cars = JsonUtils.getObjectFromFile(CARS, Cars.class);
        CarsDTOResponse carsDTOResponse = JsonUtils.getObjectFromFile(CARS, CarsDTOResponse.class);

        when(carsRepository.findById(ID_car)).thenReturn(Optional.ofNullable(cars));

        when(carsRepository.save(cars)).thenReturn(cars);
        CarsDTOResponse response = carsService.updateCar(carsDTORequest, ID_car);

        assertNotNull(response);
        assertEquals(carsDTOResponse, response);
    }

    @Test
    void deleteCarById() throws IOException {
        String ID_car = "1";
        Cars car = JsonUtils.getObjectFromFile(CARS, Cars.class);
        when(carsRepository.findById(ID_car)).thenReturn(Optional.ofNullable(car));

        assertEquals("Car deleted! With id: " + ID_car, carsService.deleteCarById(ID_car));
    }

    @Test
    void deleteCarById_ERROR_CarNotFoundException() {
        when(carsRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.class, () -> carsService.deleteCarById(anyString()));
    }

    @Test
    void getCarsListOnDB() throws IOException {
        Cars cars = JsonUtils.getObjectFromFile(CARS, Cars.class);
        CarsDTOResponse classDTOResponse = JsonUtils.getObjectFromFile(CARS, CarsDTOResponse.class);

        when(carsRepository.findAll()).thenReturn(List.of(cars));
        List<CarsDTOResponse> response = carsService.rafflingOffCars();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(classDTOResponse, response.get(0));
    }
}
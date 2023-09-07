package com.cars.mscars.Controller;

import com.cars.mscars.DTO.CarsDTORequest;
import com.cars.mscars.DTO.CarsDTOResponse;
import com.cars.mscars.Service.CarsService;
import com.cars.mscars.Utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.cars.mscars.Utils.JsonUtils.objectMapper;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarsController.class)
public class CarsControllerTest {

    @MockBean
    private CarsService carsService;

    @Autowired
    private MockMvc mockMvc;

    private static final String CARSDTO = "Payload/CarDTORequest.json";
    private static final String CARS = "Payload/Car.json";
    private static final String ID_CAR = "1";

    @Test
    void findById() throws Exception {
        CarsDTOResponse car = JsonUtils.getObjectFromFile(CARS, CarsDTOResponse.class);

        when(carsService.findCarById(ID_CAR)).thenReturn(car);

        mockMvc.perform(get("/api/v1/cars/get/{id}", ID_CAR))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_CAR))
                .andExpect(content().json(objectMapper.writeValueAsString(car)));

        verify(carsService, times(1)).findCarById(ID_CAR);
    }

    @Test
    void findAllCars() throws Exception {
        CarsDTOResponse carsDTOResponse = JsonUtils.getObjectFromFile(CARS, CarsDTOResponse.class);

        when(carsService.findAllCars()).thenReturn(List.of(carsDTOResponse));

        mockMvc.perform(get("/api/v1/cars/get/cars"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(carsDTOResponse))));

        verify(carsService, times(1)).findAllCars();
    }

    @Test
    void createCar() throws Exception {
        CarsDTORequest carDTORequest = JsonUtils.getObjectFromFile(CARSDTO, CarsDTORequest.class);
        CarsDTOResponse carsDTOResponse = JsonUtils.getObjectFromFile(CARS, CarsDTOResponse.class);

        when(carsService.createCar(carDTORequest)).thenReturn(carsDTOResponse);

        mockMvc.perform(post("/api/v1/cars/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carDTORequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(carsDTOResponse)));

        verify(carsService, times(1)).createCar(carDTORequest);
    }

    @Test
    void updateCar() throws Exception {
        CarsDTORequest carDTORequest = JsonUtils.getObjectFromFile(CARSDTO, CarsDTORequest.class);
        CarsDTOResponse carsDTOResponse = JsonUtils.getObjectFromFile(CARS, CarsDTOResponse.class);

        when(carsService.updateCar(carDTORequest, ID_CAR)).thenReturn(carsDTOResponse);

        mockMvc.perform(put("/api/v1/cars/update/{id}", ID_CAR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carDTORequest)))
                .andExpect(jsonPath("$.id").value(ID_CAR))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(carsDTOResponse)));

        verify(carsService, times(1)).updateCar(carDTORequest, ID_CAR);
    }

    @Test
    void testDeleteById() throws Exception {
        when(carsService.deleteCarById(ID_CAR)).thenReturn("Car deleted! With id: " + ID_CAR);

        mockMvc.perform(delete("/api/v1/cars/delete/{id}", ID_CAR))
                .andExpect(status().isOk());
    }
}

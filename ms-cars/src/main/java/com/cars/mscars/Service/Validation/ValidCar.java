package com.cars.mscars.Service.Validation;

import com.cars.mscars.DTO.CarsDTORequest;
import com.cars.mscars.Entity.Cars;
import com.cars.mscars.Service.Exceptions.AlreadyRegisteredException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidCar {

    public static void validateEqualsCarsAndPilot(List<Cars> carsOnDB, CarsDTORequest carRequest) {
        for (Cars car : carsOnDB) {
            if (car.getBrand().equals(carRequest.getBrand())
                    && car.getModel().equals(carRequest.getModel())
                    && car.getYear() == carRequest.getYear()) {
                throw new AlreadyRegisteredException("Car");
            }
            if (car.getPilot().equals(carRequest.getPilot())) {
                throw new AlreadyRegisteredException("Pilot");
            }
        }
    }
}

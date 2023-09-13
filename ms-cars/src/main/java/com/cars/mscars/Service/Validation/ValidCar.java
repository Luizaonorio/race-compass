package com.cars.mscars.Service.Validation;

import com.cars.mscars.DTO.CarsDTORequest;
import com.cars.mscars.Entity.Cars;
import com.cars.mscars.Service.Exceptions.AlreadyRegisteredExceptionCar;
import com.cars.mscars.Service.Exceptions.AlreadyRegisteredExceptionPilot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidCar {

    public static void validateEqualsCarsAndPilot(List<Cars> carsOnDB, CarsDTORequest carRequest) {
        for (Cars car : carsOnDB) {
            if (car.getBrand().equals(carRequest.getBrand())
                    && car.getModel().equals(carRequest.getModel())
                    && car.getYear().equals(carRequest.getYear())) {
                throw new AlreadyRegisteredExceptionCar();
            }
            if (car.getPilot().equals(carRequest.getPilot())) {
                throw new AlreadyRegisteredExceptionPilot();
            }
        }
    }
}

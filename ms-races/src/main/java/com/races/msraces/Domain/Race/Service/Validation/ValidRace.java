package com.races.msraces.Domain.Race.Service.Validation;

import com.races.msraces.Model.CarsConversion;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ValidRace {

    final static int NUMLAPS = 5;

    public static List<CarsConversion> RunTheRace(List<CarsConversion> listCars) {
        initializeCarPositions(listCars);

        List<CarsConversion> modifiableList = new ArrayList<>(listCars);
        simulateRaceLaps(modifiableList);

        modifiableList.sort(Comparator.comparingInt(CarsConversion::getPosition));
        return modifiableList;
    }

    private static void initializeCarPositions(List<CarsConversion> carsListRandom) {
        for (int i = 0; i < carsListRandom.size(); i++) {
            carsListRandom.get(i).setPosition(i + 1);
        }
    }

    private static void simulateRaceLaps(List<CarsConversion> modifiableList) {
        for (int lap = 0; lap < NUMLAPS; lap++) {
            Set<CarsConversion> carsThatAlreadyOvertake = new HashSet<>();

            updateCarVelocities(modifiableList);
            performOvertaking(modifiableList, carsThatAlreadyOvertake);
        }
    }

    private static void updateCarVelocities(List<CarsConversion> modifiableList) {
        for (CarsConversion car : modifiableList) {
            int randomVelocity = 100 + (int) (Math.random() * 101);
            car.setVelocity(randomVelocity);
        }
    }

    private static void performOvertaking(List<CarsConversion> modifiableList, Set<CarsConversion> carsThatAlreadyOvertake) {
        for (int i = 0; i < modifiableList.size() - 1; i++) {
            CarsConversion currentCar = modifiableList.get(i);
            CarsConversion nextCar = modifiableList.get(i + 1);

            if (currentCar.getVelocity() > nextCar.getVelocity() &&
                    !carsThatAlreadyOvertake.contains(currentCar)) {

                int tempPosition = currentCar.getPosition();
                currentCar.setPosition(nextCar.getPosition());
                nextCar.setPosition(tempPosition);

                carsThatAlreadyOvertake.add(currentCar);
            }
        }
    }
}


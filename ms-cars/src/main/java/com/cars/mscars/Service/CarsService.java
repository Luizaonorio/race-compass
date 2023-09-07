package com.cars.mscars.Service;

import com.cars.mscars.DTO.CarsDTORequest;
import com.cars.mscars.DTO.CarsDTOResponse;
import com.cars.mscars.Entity.Cars;
import com.cars.mscars.Repositoty.CarsRepository;
import com.cars.mscars.Service.Exceptions.CarNotFoundException;
import com.cars.mscars.Service.Validation.ValidCar;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarsService {

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private ModelMapper mapper;

    public CarsDTOResponse findCarById(String id) {
        Cars carEntity = carsRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        CarsDTOResponse carResponse = mapper.map(carEntity, CarsDTOResponse.class);
        return carResponse;
    }

    public List<CarsDTOResponse> findAllCars() {
        List<Cars> carsListEntity = getCarsListOnDB();
        List<CarsDTOResponse> carslistResponse = carsListEntity.stream().map(car -> mapper.map(car, CarsDTOResponse.class)).toList();
        return carslistResponse;
    }

    public CarsDTOResponse createCar(CarsDTORequest carsDTORequest) {
        ValidCar.validateEqualsCarsAndPilot(getCarsListOnDB(), carsDTORequest);
        Cars carsConversion = mapper.map(carsDTORequest, Cars.class);
        Cars carSaveEntity = carsRepository.save(carsConversion);
        CarsDTOResponse carResponse = mapper.map(carSaveEntity, CarsDTOResponse.class);
        return carResponse;
    }

    public CarsDTOResponse updateCar(CarsDTORequest carsDTORequest, String id) {
        Cars cars = carsRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        ValidCar.validateEqualsCarsAndPilot(getCarsListOnDB(), carsDTORequest);
        cars.setBrand(carsDTORequest.getBrand());
        cars.setModel(carsDTORequest.getModel());
        cars.setYear(carsDTORequest.getYear());
        cars.setPilot(cars.getPilot());

        carsRepository.save(cars);
        CarsDTOResponse carResponse = mapper.map(cars, CarsDTOResponse.class);
        return carResponse;
    }

    public String deleteCarById(String id) {
        carsRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        carsRepository.deleteById(id);
        String carDeletedResponse = "Car deleted! With id: " + id;
        return carDeletedResponse;
    }

    public List<Cars> getCarsListOnDB() {
        List<Cars> carsListEntity = carsRepository.findAll();
        return carsListEntity;
    }
}

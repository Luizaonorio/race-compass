package com.cars.mscars.Repositoty;


import com.cars.mscars.Entity.Cars;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CarsRepository extends MongoRepository<Cars, String> {

}

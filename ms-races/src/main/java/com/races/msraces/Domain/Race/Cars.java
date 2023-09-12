package com.races.msraces.Domain.Race;

import com.races.msraces.Model.CarsConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ms-cars", url = "localhost:8088")
public interface Cars {
    @GetMapping(value = "/api/v1/cars/sortedCars")
    List<CarsConversion> rafflingOffCars();
}

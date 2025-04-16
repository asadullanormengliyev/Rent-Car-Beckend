package pdp.uz.rentcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pdp.uz.rentcar.dtos.car.request.CarCreateRequest;
import pdp.uz.rentcar.dtos.car.response.CarCreateResponse;
import pdp.uz.rentcar.service.CarService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cars")
public class CarController {
    private final CarService carService;

    @PreAuthorize("hasRole('ADMIN')")
     @PostMapping("/add")
    public CarCreateResponse createCar(@RequestBody CarCreateRequest carCreateRequest) throws IOException {
         return carService.createCar(carCreateRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/car/{id}")
    public CarCreateResponse getCarById(@PathVariable UUID id) {
        return carService.getCarById(id);
    }

    @GetMapping("/list")
    public List<CarCreateResponse> getCars() {
        return carService.findAllCars();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public void deleteCarById(@PathVariable UUID id) {
        carService.deleteCarById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CarCreateResponse>> searchCars(
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String transmission,
            @RequestParam(required = false) Integer seats,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer year
    ) {
        List<CarCreateResponse> result = carService.searchCars(
                model, name, minPrice, maxPrice, location, transmission, seats, category, year);
        return ResponseEntity.ok(result);
    }


}

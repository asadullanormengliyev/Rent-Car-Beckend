package pdp.uz.rentcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.uz.rentcar.dtos.carCategory.request.CarCategoryRequest;
import pdp.uz.rentcar.dtos.carCategory.request.CarCategoryUpdateRequest;
import pdp.uz.rentcar.dtos.carCategory.response.CarCategoryResponse;
import pdp.uz.rentcar.service.CarCategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car-category")
public class CarCategoryController {
    private final CarCategoryService carCategoryService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public CarCategoryResponse createCarCategory(@RequestBody CarCategoryRequest carCategoryRequest) {
       return carCategoryService.create(carCategoryRequest);
    }

    @GetMapping("/list")
    public List<CarCategoryResponse>  getCarCategories() {
        return carCategoryService.getCarCategories();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/car-category/{id}")
   public CarCategoryResponse getCarCategoryById(@PathVariable UUID id) {
      return carCategoryService.getCarCategoryById(id);
   }
    @PreAuthorize("hasRole('ADMIN')")
   @DeleteMapping("/delete/{id}")
   public boolean deleteCarCategoryById(@PathVariable UUID id) {
        return carCategoryService.deleteCarCategoryById(id);
   }
    @PreAuthorize("hasRole('ADMIN')")
   @PutMapping("/update")
   private CarCategoryResponse update(@RequestBody CarCategoryUpdateRequest carCategoryUpdateRequest) {
      return   carCategoryService.update(carCategoryUpdateRequest);
   }
}

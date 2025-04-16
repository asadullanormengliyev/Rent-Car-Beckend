package pdp.uz.rentcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.uz.rentcar.dtos.location.request.LocationCreateRequest;
import pdp.uz.rentcar.dtos.location.request.LocationDeleteRequest;
import pdp.uz.rentcar.dtos.location.request.LocationUpdateRequest;
import pdp.uz.rentcar.dtos.location.response.LocationCreateResponse;
import pdp.uz.rentcar.dtos.location.response.LocationDeleteResponse;
import pdp.uz.rentcar.dtos.location.response.LocationResponse;
import pdp.uz.rentcar.dtos.location.response.LocationUpdateResponse;
import pdp.uz.rentcar.service.LocationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/location")
public class LocationController {

     private final LocationService locationService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    private LocationCreateResponse create(@RequestBody LocationCreateRequest request) {
         return locationService.create(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    private LocationDeleteResponse delete(@RequestBody LocationDeleteRequest request) {
        return locationService.delete(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    private LocationUpdateResponse update(@RequestBody LocationUpdateRequest request) {
        return locationService.update(request);
    }

    @GetMapping("/locations")
    public List<LocationResponse> getAll() {
        return locationService.getAll();
    }
}



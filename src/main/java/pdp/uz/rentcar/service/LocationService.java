package pdp.uz.rentcar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.rentcar.dtos.location.request.LocationCreateRequest;
import pdp.uz.rentcar.dtos.location.request.LocationDeleteRequest;
import pdp.uz.rentcar.dtos.location.request.LocationUpdateRequest;
import pdp.uz.rentcar.dtos.location.response.LocationCreateResponse;
import pdp.uz.rentcar.dtos.location.response.LocationDeleteResponse;
import pdp.uz.rentcar.dtos.location.response.LocationResponse;
import pdp.uz.rentcar.dtos.location.response.LocationUpdateResponse;
import pdp.uz.rentcar.entity.Location;
import pdp.uz.rentcar.exception.RecordNotFoundException;
import pdp.uz.rentcar.repository.LocationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationCreateResponse create(LocationCreateRequest request) {
        Location location = new Location();
        location.setCity(request.getLocation());
        locationRepository.save(location);
        return new LocationCreateResponse(location.getId(), request.getLocation());
    }

    public LocationDeleteResponse delete(LocationDeleteRequest request) {
        Optional<Location> byId = locationRepository.findById(request.getId());
        if (byId.isEmpty()) {
            throw new RecordNotFoundException("Location not found");
        }
        Location location = byId.get();
        locationRepository.deleteById(request.getId());
        return new LocationDeleteResponse(location.getCity());
    }

    public LocationUpdateResponse update(LocationUpdateRequest request) {
        Optional<Location> byId = locationRepository.findById(request.getId());
        if (byId.isPresent()) {
            Location location = byId.get();
            location.setCity(request.getNewLocation());
            Location save = locationRepository.save(location);
            return new LocationUpdateResponse(save.getCity());
        }
        throw new RecordNotFoundException("Location not found");
    }

    public List<LocationResponse> getAll() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(location -> new LocationResponse(location.getId(), location.getCity())).collect(Collectors.toList());
    }
}

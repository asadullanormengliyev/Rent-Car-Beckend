package pdp.uz.rentcar.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pdp.uz.rentcar.dtos.car.request.CarCreateRequest;
import pdp.uz.rentcar.dtos.car.response.CarCreateResponse;
import pdp.uz.rentcar.entity.*;
import pdp.uz.rentcar.entity.enums.CarStatus;
import pdp.uz.rentcar.exception.RecordNotFoundException;
import pdp.uz.rentcar.repository.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.persistence.criteria.Predicate;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarCategoryRepository carCategoryRepository;
    private final AttachmentRepository attachmentRepository;
    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;

    public CarCreateResponse createCar(CarCreateRequest carRequest) throws IOException {
        Optional<CarCategory> byId = carCategoryRepository.findById(carRequest.getCarCategoryId());
        if (byId.isEmpty()) {
            throw new RecordNotFoundException("Car Category Not Found");
        }
        CarCategory carCategory = byId.get();
        Optional<Location> optionalLocation = locationRepository.findById(carRequest.getLocationId());
        if (optionalLocation.isEmpty()) {
            throw new RecordNotFoundException("Location Not Found");
        }
        CarAttachment carAttachment = attachmentRepository.findById(carRequest.getAttachmentId()).orElseThrow(() -> new RecordNotFoundException("Attachment Not Found"));

        Location location = optionalLocation.get();
        Car car = Car.builder()
                .carNumber(carRequest.getCarNumber())
                .model(carRequest.getModel())
                .color(carRequest.getColor())
                .seats(carRequest.getSeats())
                .year(carRequest.getYear())
                .mileage(carRequest.getMileage())
                .carCategory(carCategory)
                .pricePerDay(carRequest.getPricePerDay())
                .transmission(carRequest.getTransmission())
                .name(carRequest.getName())
                .location(location)
                .attachment(carAttachment)
                .build();
        car.setStatus(CarStatus.AVAILABLE);

        Car save = carRepository.save(car);

        return convertToDto(save);
    }

    public List<CarCreateResponse> findAllCars() {
        List<Car> all = carRepository.findAll();
        return all.stream().map(car -> modelMapper.map(car, CarCreateResponse.class)).toList();
    }

    public CarCreateResponse getCarById(UUID id) {
        Car carNotFound = carRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Car Not Found"));
        return modelMapper.map(carNotFound, CarCreateResponse.class);
    }
    public List<CarCreateResponse> searchCars(String model, String name, Double minPrice, Double maxPrice,
                                              String location, String transmission, Integer seats,
                                              String category, Integer year) {
        List<Car> cars = carRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (model != null && !model.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("model")), "%" + model.toLowerCase() + "%"));
            }
            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("pricePerDay"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("pricePerDay"), maxPrice));
            }
            if (location != null && !location.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("location").get("city")), "%" + location.toLowerCase() + "%"));
            }
            if (transmission != null && !transmission.isEmpty()) {
                predicates.add(cb.equal(cb.lower(root.get("transmission")), transmission.toLowerCase()));
            }
            if (seats != null) {
                predicates.add(cb.equal(root.get("seats"), seats));
            }
            if (category != null && !category.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("carCategory").get("name")), "%" + category.toLowerCase() + "%"));
            }
            if (year != null) {
                predicates.add(cb.equal(root.get("year"), year.toString()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });

        return cars.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private CarCreateResponse convertToDto(Car save) {
       return CarCreateResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .color(save.getColor())
                .seats(save.getSeats())
                .year(save.getYear())
                .mileage(save.getMileage())
                .carCategoryName(save.getCarCategory().getName())
                .pricePerDay(save.getPricePerDay())
                .transmission(save.getTransmission())
                .carCategoryId(save.getCarCategory().getId())
                .attachmentId(save.getAttachment().getId())
                .model(save.getModel())
                .carNumber(save.getCarNumber())
                .locationId(save.getLocation().getId())
                .status(save.getStatus())
               .locationId(save.getLocation().getId())
               .build();
    }

    public void deleteCarById(UUID id) {
        carRepository.deleteById(id);
    }
}

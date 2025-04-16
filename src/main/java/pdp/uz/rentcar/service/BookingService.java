package pdp.uz.rentcar.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pdp.uz.rentcar.dtos.booking.request.BookingCreateRequest;
import pdp.uz.rentcar.dtos.booking.response.BookingResponse;
import pdp.uz.rentcar.entity.Booking;
import pdp.uz.rentcar.entity.Car;
import pdp.uz.rentcar.entity.enums.BookingStatus;
import pdp.uz.rentcar.exception.RecordNotFoundException;
import pdp.uz.rentcar.repository.BookingRepository;
import pdp.uz.rentcar.repository.CarRepository;
import pdp.uz.rentcar.repository.LocationRepository;
import pdp.uz.rentcar.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;

    public BookingResponse create(BookingCreateRequest request) {
        Booking booking = bookingRepository.save(toBooking(request));
        return modelMapper.map(booking, BookingResponse.class);
    }

    private Booking toBooking(BookingCreateRequest request) {
        Optional<Car> byId = carRepository.findById(request.getCarId());

        if (byId.isEmpty()) {
            throw new RecordNotFoundException("Car not found");
        }

        double totalPrice = getTotalPrice(request.getStartTime(), request.getEndTime(), request.getCarId());
        Car car = byId.get();
        Booking booking = new Booking();
        booking.setCar(car);
        booking.setUser(userRepository.findById(request.getUserId()).orElse(null));
        booking.setCreated(LocalDateTime.now());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.PENDING);
        booking.setPickupLocation(List.of(Objects.requireNonNull(locationRepository.findById(car.getLocation().getId()).orElse(null))));
        booking.setDropOffLocation(List.of(Objects.requireNonNull(locationRepository.findById(request.getDropOffLocationId()).orElse(null))));
        return booking;
    }

    public double getTotalPrice(LocalDateTime startTime, LocalDateTime endTime, UUID carId){
        Car car = carRepository.findById(carId).orElseThrow(() -> new RecordNotFoundException("Car not found"));

        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("startTime, endTime, or carId cannot be null");
        }

        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }

        long days = ChronoUnit.DAYS.between(startTime.toLocalDate(), endTime.toLocalDate());
        if (days == 0){
            days = 1;
        }
        return days * car.getPricePerDay();
    }

    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return modelMapper.map(bookings, List.class);
    }

    public void deleteBooking(UUID bookingId) {
        bookingRepository.deleteById(bookingId);
    }


}

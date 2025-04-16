package pdp.uz.rentcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.uz.rentcar.dtos.booking.request.BookingCreateRequest;
import pdp.uz.rentcar.dtos.booking.response.BookingResponse;
import pdp.uz.rentcar.service.BookingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    private final BookingService bookingService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public BookingResponse create(@RequestBody BookingCreateRequest request) {
        return bookingService.create(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/calculate-price")
    public Double calculatePrice(@RequestParam UUID carId,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return bookingService.getTotalPrice(startTime, endTime, carId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public void deleteBooking(@PathVariable UUID id) {
        bookingService.deleteBooking(id);
    }


}

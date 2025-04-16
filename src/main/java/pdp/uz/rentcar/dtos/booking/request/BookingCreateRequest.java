package pdp.uz.rentcar.dtos.booking.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingCreateRequest {
    private UUID userId;
    private UUID carId;
    private UUID dropOffLocationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

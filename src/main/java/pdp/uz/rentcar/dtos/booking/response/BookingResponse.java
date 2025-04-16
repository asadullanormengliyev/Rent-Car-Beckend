package pdp.uz.rentcar.dtos.booking.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;
import pdp.uz.rentcar.entity.Car;
import pdp.uz.rentcar.entity.Location;
import pdp.uz.rentcar.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingResponse {
    private UUID id;
    private double totalPrice;
    private List<Location> pickupLocation;
    private List<Location> dropOffLocation;
    private String startTime;
    private String endTime;
    private String created;
    private String status;
    private User user;
    private Car car;
}

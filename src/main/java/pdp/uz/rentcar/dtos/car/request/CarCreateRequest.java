package pdp.uz.rentcar.dtos.car.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarCreateRequest {
    private String name;
    private String model;
    private String carNumber;
    private String color;
    private String transmission;
    private String mileage;
    private int seats;
    private String year;
    private double pricePerDay;
    private UUID carCategoryId;
    private UUID locationId;
    private UUID attachmentId;
}

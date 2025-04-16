package pdp.uz.rentcar.dtos.location.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationUpdateRequest {
    private UUID id;
    private String newLocation;
}

package pdp.uz.rentcar.dtos.location.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationCreateResponse {

    private UUID id;
    private String location;
}

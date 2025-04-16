package pdp.uz.rentcar.dtos.carCategory.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarCategoryUpdateRequest {
    private UUID id;
    private String name;
}

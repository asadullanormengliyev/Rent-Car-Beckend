package pdp.uz.rentcar.dtos.review.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewCreateResponse {

    private UUID id;
    private UUID userId;
    private UUID carId;
    private int rating;
    private String comment;
    private LocalDateTime created;
}

package pdp.uz.rentcar.dtos.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String phoneNumber;
}

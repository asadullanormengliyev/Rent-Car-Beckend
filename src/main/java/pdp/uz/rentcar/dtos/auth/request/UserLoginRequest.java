package pdp.uz.rentcar.dtos.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {
    @NotBlank(message = "Username bo'sh bo'lishi mumkin emas")
    @Size(min = 4, max = 20, message = "Username uzunligi 4 dan 20 gacha bo‘lishi kerak")
    private String username;

    @NotBlank(message = "Parol bo'sh bo'lishi mumkin emas")
    @Size(min = 8, max = 100, message = "Parol uzunligi 8 dan 100 gacha bo‘lishi kerak")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Parol kamida bitta katta harf, bitta kichik harf, bitta raqam va maxsus belgi (@#$%^&+=) dan iborat bo‘lishi kerak"
    )
    private String password;
}

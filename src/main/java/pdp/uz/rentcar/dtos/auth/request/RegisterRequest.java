package pdp.uz.rentcar.dtos.auth.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Ism bo'sh bo'lishi mumkin emas")
    @Size(min = 2, max = 50, message = "Ism uzunligi 2 dan 50 gacha bo‘lishi kerak")
    private String firstName;

    @NotBlank(message = "Familiya bo'sh bo'lishi mumkin emas")
    @Size(min = 2, max = 50, message = "Familiya uzunligi 2 dan 50 gacha bo‘lishi kerak")
    private String lastName;

    @NotBlank(message = "Email bo'sh bo'lishi mumkin emas")
    @Email(message = "Email noto‘g‘ri formatda kiritilgan")
    private String email;

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

    @NotBlank(message = "Telefon raqam bo'sh bo'lishi mumkin emas")
    @Pattern(
            regexp = "^(\\+998)?[0-9]{9}$",
            message = "Telefon raqam +998 yoki 9 xonali formatda bo‘lishi kerak"
    )
    private String phoneNumber;
}


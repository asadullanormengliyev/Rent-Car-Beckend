package pdp.uz.rentcar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.rentcar.dtos.auth.request.RefreshTokenRequest;
import pdp.uz.rentcar.dtos.auth.request.RegisterRequest;
import pdp.uz.rentcar.dtos.auth.request.UserLoginRequest;
import pdp.uz.rentcar.dtos.auth.response.LoginResponse;
import pdp.uz.rentcar.dtos.auth.response.RefreshTokenResponse;
import pdp.uz.rentcar.dtos.auth.response.RegisterResponse;
import pdp.uz.rentcar.entity.User;
import pdp.uz.rentcar.mapper.AuthConvertor;
import pdp.uz.rentcar.service.AuthService;
import pdp.uz.rentcar.service.jwt.TokenService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/register")
    private RegisterResponse create(@RequestBody @Valid RegisterRequest request) {
        User entity = AuthConvertor.registerRequestToEntity(request);
        User user = authService.create(entity);
        return AuthConvertor.entityToRegisterResponse(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid UserLoginRequest request)
            throws JsonProcessingException {
        return authService.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("/refresh")
    public RefreshTokenResponse refreshToken(
            @RequestBody RefreshTokenRequest request
    ) throws JsonProcessingException {
        String accessToken = tokenService.generateAccessToken(request);
        return new RefreshTokenResponse("Bearer " + accessToken);
    }

}

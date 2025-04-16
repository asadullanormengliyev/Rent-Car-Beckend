package pdp.uz.rentcar.mapper;

import pdp.uz.rentcar.dtos.auth.request.RegisterRequest;
import pdp.uz.rentcar.dtos.auth.response.RegisterResponse;
import pdp.uz.rentcar.entity.User;
import pdp.uz.rentcar.entity.enums.AuthProvider;

public class AuthConvertor {
    public static User registerRequestToEntity(RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setProvider(AuthProvider.LOCAL);
        return user;
    }

    public static RegisterResponse entityToRegisterResponse(User user) {
        RegisterResponse dto = new RegisterResponse();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }
}

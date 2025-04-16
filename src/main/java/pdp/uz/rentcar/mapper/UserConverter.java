package pdp.uz.rentcar.mapper;

import pdp.uz.rentcar.dtos.user.request.UserCreateRequest;
import pdp.uz.rentcar.dtos.user.response.UserResponse;
import pdp.uz.rentcar.entity.User;
import pdp.uz.rentcar.entity.enums.AuthProvider;

public class UserConverter {
    public static User toEntity(UserCreateRequest requestDto) {
        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());
        user.setPhoneNumber(requestDto.getPhoneNumber());
        user.setProvider(AuthProvider.LOCAL);
        return user;
    }

    public static UserResponse toDto(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }
}

package pdp.uz.rentcar.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.uz.rentcar.mapper.UserConverter;
import pdp.uz.rentcar.dtos.user.request.UserCreateRequest;
import pdp.uz.rentcar.dtos.user.response.UserResponse;
import pdp.uz.rentcar.entity.User;
import pdp.uz.rentcar.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    private UserResponse create(@RequestBody @Valid UserCreateRequest requestDto) {
        User entity = UserConverter.toEntity(requestDto);
        User user = userService.create(entity);
        return UserConverter.toDto(user);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable UUID id) {
        return UserConverter.toDto(userService.getUserById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
      return userService.getAllUsers().stream().map(UserConverter::toDto).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable("id") UUID id) {
       return  userService.deleteUserById(id);
    }


}

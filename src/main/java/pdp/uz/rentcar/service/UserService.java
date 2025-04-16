package pdp.uz.rentcar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pdp.uz.rentcar.dtos.user.response.UserResponse;
import pdp.uz.rentcar.entity.Role;
import pdp.uz.rentcar.entity.User;
import pdp.uz.rentcar.entity.enums.UserRole;
import pdp.uz.rentcar.repository.RoleRepository;
import pdp.uz.rentcar.repository.UserRepository;
import pdp.uz.rentcar.service.jwt.JwtService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(getUserRole()));
        return userRepository.save(user);
    }

    private Role getUserRole() {
        return roleRepository.findByRoles(UserRole.USER);
    }

    public User getUserById(UUID userId) {
        Optional<User> optionalUserEntity = userRepository.findById(userId);
        if (optionalUserEntity.isEmpty()) {
            throw new IllegalStateException(String.format("User with id %s not found", userId));
        }
        return optionalUserEntity.get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUserById(UUID userId) {
        Optional<User> optionalUserEntity = userRepository.findById(userId);
        if (optionalUserEntity.isEmpty()) {
            throw new IllegalStateException(String.format("User with id %s not found", userId));
        }
        userRepository.deleteById(userId);
        return true;
    }


}

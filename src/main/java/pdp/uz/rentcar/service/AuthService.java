package pdp.uz.rentcar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pdp.uz.rentcar.dtos.auth.response.LoginResponse;
import pdp.uz.rentcar.entity.Role;
import pdp.uz.rentcar.entity.User;
import pdp.uz.rentcar.entity.enums.UserRole;
import pdp.uz.rentcar.repository.RoleRepository;
import pdp.uz.rentcar.repository.UserRepository;
import pdp.uz.rentcar.service.jwt.JwtService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(String username, String password) throws JsonProcessingException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        User userEntity = optionalUser.get();
        boolean matches = passwordEncoder.matches(password, userEntity.getPassword());
        if (!matches) {
            throw new UsernameNotFoundException(username);
        }
        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);
        return new LoginResponse("Bearer " + accessToken, refreshToken);
    }

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(getUserRole()));
        return userRepository.save(user);
    }


    private Role getUserRole() {
        return roleRepository.findByRoles(UserRole.USER);
    }
}

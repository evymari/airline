package com.f5.Airline.users;

import com.f5.Airline.roles.Role;
import com.f5.Airline.roles.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registro público (rol USER)
    public User registerPublicUser(UserDto userDto) {
        validateUser(userDto);

        User user = new User(userDto.username(), userDto.email(), passwordEncoder.encode(userDto.password()));
        user.setPhotoUrl(userDto.photoUrl());

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));

        user.setRoles(Set.of(userRole));
        return userRepository.save(user);
    }

    // Crear usuario como admin con rol asignado
    public UserResponseDto createUserByAdmin(UserDto userDto, String roleName) {
        validateUser(userDto);

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + roleName));

        User user = new User(userDto.username(), userDto.email(), passwordEncoder.encode(userDto.password()));
        user.setPhotoUrl(userDto.photoUrl());
        user.setRoles(Set.of(role));
        return mapToResponseDto(userRepository.save(user));
    }

    private UserResponseDto mapToResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhotoUrl()
        );
    }


    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }


    public Optional<UserResponseDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::mapToResponseDto);
    }


    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void validateUser(UserDto dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email ya registrado");
        }
        if (userRepository.existsByUsername(dto.username())) {
            throw new IllegalArgumentException("Username ya registrado");
        }
    }
}

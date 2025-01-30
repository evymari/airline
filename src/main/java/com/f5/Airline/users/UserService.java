package com.f5.Airline.users;
/*
import com.f5.Airline.profiles.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener un usuario por ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Verificar si existe un usuario con el email proporcionado
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Crear un usuario nuevo con validaciones
    public User createUser(UserDto userDto) {
        if (existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Configurar el perfil si está disponible
        if (userDto.getProfile() != null) {
            Profile profile = userDto.getProfile();
            profile.setUser(user);
            user.setProfile(profile);
        }

        return userRepository.save(user);
    }

    // Actualizar datos de un usuario existente
    public Optional<User> updateUser(Long id, UserDto userDto) {
        return userRepository.findById(id).map(user -> {
            if (!user.getEmail().equals(userDto.getEmail()) && existsByEmail(userDto.getEmail())) {
                throw new IllegalArgumentException("El email ya está registrado por otro usuario");
            }

            user.setEmail(userDto.getEmail());
            if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }

            return userRepository.save(user);
        });
    }

    // Eliminar un usuario por ID
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
*/
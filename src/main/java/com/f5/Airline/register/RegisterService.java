package com.f5.Airline.register;

import com.f5.Airline.facade.encryptions.IEncryptFacade;

import com.f5.Airline.register.dto.RegisterRequestDTO;
import com.f5.Airline.register.dto.RegisterResponseDTO;
import com.f5.Airline.roles.RoleService;
import com.f5.Airline.users.User;
import com.f5.Airline.users.UserRepository;
import com.f5.Airline.validation.ValidationException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final IEncryptFacade encryptFacade;


    public RegisterService(UserRepository userRepository,
                           RoleService roleService,
                           IEncryptFacade encryptFacade)
                            {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encryptFacade = encryptFacade;

    }

    public RegisterResponseDTO save(@Valid RegisterRequestDTO userData) {
        validateUser(userData);

        String passwordEncoded = encryptFacade.encode("bcrypt", userData.getPassword());

        // Crear usuario
        User newUser = new User(userData.getUsername(), userData.getEmail(), passwordEncoded);
        newUser.setPhotoUrl(userData.getPhotoUrl());
        newUser.setRoles(roleService.assignDefaultRole());

        userRepository.save(newUser);



        return new RegisterResponseDTO(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getEmail()
        );


    }

    private void validateUser(RegisterRequestDTO userData) {
        if (userRepository.existsByUsername(userData.getUsername())) {
            throw new ValidationException("El username ya está en uso.");
        }
        if (userRepository.existsByEmail(userData.getEmail())) {
            throw new ValidationException("El email ya está en uso.");
        }
    }
}

package com.f5.Airline.register;

import com.f5.Airline.facade.encryptions.IEncryptFacade;
import com.f5.Airline.profiles.Profile;
import com.f5.Airline.profiles.ProfileRepository;
import com.f5.Airline.countries.Country;
import com.f5.Airline.countries.CountryRepository;
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
    private final ProfileRepository profileRepository;
    private final CountryRepository countryRepository; // 👈 agregado

    public RegisterService(UserRepository userRepository,
                           RoleService roleService,
                           ProfileRepository profileRepository,
                           CountryRepository countryRepository,
                           IEncryptFacade encryptFacade) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.roleService = roleService;
        this.countryRepository = countryRepository;
        this.encryptFacade = encryptFacade;
    }

    public RegisterResponseDTO save(@Valid RegisterRequestDTO userData) {
        validateUser(userData);

        String passwordEncoded = encryptFacade.encode("bcrypt", userData.getPassword());

        // 1️⃣ Crear usuario
        User newUser = new User(userData.getUsername(), userData.getEmail(), passwordEncoded);
        newUser.setPhotoUrl(userData.getPhotoUrl());
        newUser.setRoles(roleService.assignDefaultRole());
        userRepository.save(newUser);

        // 2️⃣ Crear perfil asociado automáticamente
        Profile profile = new Profile();
        profile.setUser(newUser);
        profile.setEmail(newUser.getEmail());

        // 3️⃣ Asignar Country (por defecto o del DTO)
        Country country;
        if (userData.getCountryId() != null) { // si el DTO trae un countryId
            country = countryRepository.findById(userData.getCountryId())
                    .orElseThrow(() -> new RuntimeException("País no válido"));
        } else {
            country = countryRepository.findById(1L) // ID del país por defecto
                    .orElseThrow(() -> new RuntimeException("País no encontrado"));
        }
        profile.setCountry(country);

        profileRepository.save(profile);

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

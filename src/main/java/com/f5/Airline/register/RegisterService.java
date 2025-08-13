package com.f5.Airline.register;


import com.f5.Airline.facade.encryptions.IEncryptFacade;
import com.f5.Airline.profiles.Profile;
import com.f5.Airline.profiles.ProfileRepository;
import com.f5.Airline.roles.RoleService;
import com.f5.Airline.users.User;
import com.f5.Airline.users.UserDto;
import com.f5.Airline.users.UserRepository;
import com.f5.Airline.validation.ValidationException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

import java.util.Map;

@Service
public class RegisterService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final IEncryptFacade encryptFacade;


    public RegisterService(ProfileRepository profileRepository, UserRepository userRepository, RoleService roleService, IEncryptFacade encryptFacade) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encryptFacade = encryptFacade;
    }

    public Map<String, String> save(@Valid UserDto userData) {
        if (userRepository.existsByUsername(userData.username())) {
            throw new ValidationException("The username is already in use.");
        }

       // String passwordDecoded = encryptFacade.decode("base64", userData.password());
        String passwordEncoded = encryptFacade.encode("bcrypt", userData.password());

        User newUser = new User(userData.username(), userData.email(), passwordEncoded);
        newUser.setRoles(roleService.assignDefaultRole());

        Profile profile = new Profile();
        profile.setEmail(userData.email());
        profile.setAddress("Default address");
        profile.setPhotoUrl(userData.photoUrl());
        profile.setUser(newUser);

        newUser.setProfile(profile);
        userRepository.save(newUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario registrado con perfil");
        return response;
    }
}
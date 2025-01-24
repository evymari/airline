package com.f5.Airline.register;


import com.f5.Airline.facade.encryptions.IEncryptFacade;
import com.f5.Airline.profiles.Profile;
import com.f5.Airline.profiles.ProfileRepository;
import com.f5.Airline.roles.RoleService;
import com.f5.Airline.users.User;
import com.f5.Airline.users.UserDto;
import com.f5.Airline.users.UserRepository;
import com.f5.Airline.validation.ValidationException;
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

    public Map<String, String> save(UserDto userData) {
        // Validar el formato del correo electrónico
        if (!isValidEmail(userData.email())) {
            throw new ValidationException("The email is not valid.");
        }

        // Validar que la contraseña no sea nula ni demasiado corta
        if (!StringUtils.hasText(userData.password()) || userData.password().length() < 8) {
            throw new ValidationException("The password must be at least 8 characters.");
        }
        String passwordDecoded = encryptFacade.decode("base64", userData.password());

        System.out.println("<------------ " + passwordDecoded);

        String passwordEncoded = encryptFacade.encode("bcrypt", passwordDecoded);

        User newUser = new User(userData.email(), passwordEncoded);
        newUser.setRoles(roleService.assignDefaultRole());

        // Crear perfil asociado
        Profile profile = new Profile();
        profile.setEmail(userData.email());
        profile.setAddress("Default address"); // Se puede personalizar
        profile.setUser(newUser);
        // Establecer perfil en el usuario
        newUser.setProfile(profile);
        userRepository.save(newUser);


        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario registrado con perfil");

        return response;

    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        return email != null && email.matches(emailRegex);

    }
}
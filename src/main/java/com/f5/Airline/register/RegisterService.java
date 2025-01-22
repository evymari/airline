package com.f5.Airline.register;


import com.f5.Airline.facade.encryptions.IEncryptFacade;
import com.f5.Airline.roles.RoleService;
import com.f5.Airline.users.User;
import com.f5.Airline.users.UserDto;
import com.f5.Airline.users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import java.util.Map;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final IEncryptFacade encryptFacade;

    public RegisterService(UserRepository userRepository, RoleService roleService, IEncryptFacade encryptFacade) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encryptFacade = encryptFacade;
    }

    public Map<String, String> save(UserDto userData) {

        String passwordDecoded = encryptFacade.decode("base64",userData.password());

        System.out.println("<------------ " + passwordDecoded);

        String passwordEncoded = encryptFacade.encode("bcrypt", passwordDecoded);

        User newUser = new User(userData.username(), passwordEncoded);
        newUser.setRoles(roleService.assignDefaultRole());

        userRepository.save(newUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Success");

        return response;
    }

}
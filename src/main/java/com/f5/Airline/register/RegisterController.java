package com.f5.Airline.register;

import com.f5.Airline.register.dto.RegisterRequestDTO;
import com.f5.Airline.register.dto.RegisterResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "${api-endpoint}/register")
public class RegisterController {

    private final RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO newUser) {
        RegisterResponseDTO response = service.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

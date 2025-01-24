package com.f5.Airline.auth;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "${api-endpoint}")
public class AuthController {

    @GetMapping(path = "/login")
    public ResponseEntity<Map<String, String>> login() {
        Map<String, String> json = new HashMap<>();

        try {
            SecurityContext contextHolder = SecurityContextHolder.getContext();
            Authentication auth = contextHolder.getAuthentication();

            // Verificar si el usuario está autenticado
            if (auth == null || !auth.isAuthenticated()) {
                json.put("error", "Usuario no autenticado");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
            }

            json.put("message", "Logged");
            json.put("username", auth.getName());
            json.put("roles", auth.getAuthorities().iterator().next().toString());

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(json);
        } catch (Exception e) {
            json.put("error", "Error al procesar la autenticación");
            json.put("details", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }
}

/*
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api-endpoint}/auth")
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }
}*/

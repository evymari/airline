package com.f5.Airline.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "${api-endpoint}/auth")
public class AuthController {

    private final AuthenticationManager authManager;

    public AuthController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    // 🔐 POST login: valida usuario con email y contraseña
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        // Autenticación
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication authentication = authManager.authenticate(authToken);

        // Establece autenticación en contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("username", authentication.getName());
        response.put("roles", authentication.getAuthorities().toString());

        return ResponseEntity.ok(response);
    }

    // 🔎 GET login: solo devuelve info si ya estás autenticada
    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> loginStatus() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Map<String, String> json = new HashMap<>();
        json.put("message", "Already authenticated");
        json.put("username", auth.getName());
        json.put("roles", auth.getAuthorities().toString());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(json);
    }
}

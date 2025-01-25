package com.f5.Airline.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "${api-endpoint}")
public class AuthController {

    @GetMapping(path = "/login")
    public ResponseEntity<Map<String, String>> login() {
        Map<String, String> json = new HashMap<>();

        try {
            // Obtener el contexto de seguridad actual
            SecurityContext contextHolder = SecurityContextHolder.getContext();
            Authentication auth = contextHolder.getAuthentication();

            // Verificar si el usuario está autenticado
            if (auth == null || !auth.isAuthenticated() || auth.getPrincipal() instanceof String) {
                json.put("error", "Usuario no autenticado");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
            }

            // Obtener roles del usuario autenticado
            String roles = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));

            json.put("message", "Logged");
            json.put("username", auth.getName());
            json.put("roles", roles.isEmpty() ? "No roles" : roles);

            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            json.put("error", "Error al procesar la autenticación");
            json.put("details", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<Map<String, String>> logout() {
        Map<String, String> json = new HashMap<>();

        try {
            SecurityContextHolder.clearContext();
            json.put("message", "Logout exitoso");
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            json.put("error", "Error al cerrar sesión");
            json.put("details", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }
}

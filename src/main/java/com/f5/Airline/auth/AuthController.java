package com.f5.Airline.auth;

import com.f5.Airline.auth.dto.LoginRequest;
import com.f5.Airline.auth.dto.LoginResponse;
import com.f5.Airline.users.User;
import com.f5.Airline.users.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "${api-endpoint}/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authManager, UserRepository userRepository, TokenService tokenService) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    // 🔐 POST login: valida usuario con email y contraseña
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // Autenticación usando email como "username"
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(request.email(), request.password());

            Authentication authentication = authManager.authenticate(authToken);

            // Guarda la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // ✅ Generar token JWT
            String token = tokenService.generateToken(authentication);

            // ✅ Buscar al usuario en la base de datos
            User user = userRepository.findByEmail(request.email())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // ✅ Construir respuesta
            LoginResponse response = new LoginResponse(
                    token,
                    user.getUsername(),
                    user.getEmail(),
                    authentication.getAuthorities().toString()
            );

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Email o contraseña incorrectos"));
        }
    }

    // 👤 PERFIL DEL USUARIO AUTENTICADO
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "email", user.getEmail(),
                "photoUrl", user.getPhotoUrl(),
                "roles", auth.getAuthorities().toString()
        ));
    }
}

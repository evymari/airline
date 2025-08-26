package com.f5.Airline.profiles;

import com.f5.Airline.profiles.dto.ProfileRequestDTO;
import com.f5.Airline.profiles.dto.ProfileResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api-endpoint}/profiles")
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ProfileResponseDTO> createOrUpdateProfile(
            @PathVariable Long userId,
            @RequestBody ProfileRequestDTO dto) {
        return ResponseEntity.ok(service.createOrUpdateProfile(userId, dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResponseDTO> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getProfile(userId));
    }
    @GetMapping("/me")
    public ResponseEntity<ProfileResponseDTO> getMyProfile(Authentication authentication) {
        // El "sub" del JWT lo guarda Spring Security en authentication.getName()
        String email = authentication.getName();
        return ResponseEntity.ok(service.getProfileByEmail(email));
    }

}

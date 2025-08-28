package com.f5.Airline.profiles;


import com.f5.Airline.profiles.dto.ProfileResponseDTO;
import com.f5.Airline.profiles.dto.ProfileUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api-endpoint}/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/me")
    public ResponseEntity<ProfileResponseDTO> getMyProfile() {
        return ResponseEntity.ok(profileService.getMyProfile());
    }

    @PutMapping("/update")
    public ResponseEntity<ProfileResponseDTO> updateMyProfile(@RequestBody ProfileUpdateDTO dto) {
        return ResponseEntity.ok(profileService.updateMyProfile(dto));
    }
}

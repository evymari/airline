package com.f5.Airline.profiles.dto;

import com.f5.Airline.profiles.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    // Convierte Entity -> ResponseDTO
    public ProfileResponseDTO toResponseDTO(Profile profile) {
        return new ProfileResponseDTO(
                profile.getId(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getPhone(),
                profile.getUser().getId()
        );
    }

    // Convierte RequestDTO -> Entity
    public Profile toEntity(ProfileRequestDTO dto) {
        Profile profile = new Profile();
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setPhone(dto.getPhone());
        return profile;
    }
}

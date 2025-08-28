package com.f5.Airline.profiles;

import com.f5.Airline.profiles.dto.ProfileResponseDTO;

public class ProfileMapper {

    public static ProfileResponseDTO toDTO(Profile profile) {
        return new ProfileResponseDTO(
                profile.getId(),
                profile.getUser().getUsername(),
                profile.getUser().getEmail(),
                profile.getPhone(),
                profile.getAddress(),
                profile.getPhotoUrl(),
                profile.getCountry() != null ? profile.getCountry().getId() : null,   // countryId
                profile.getCountry() != null ? profile.getCountry().getName() : null // countryName
        );
    }
}

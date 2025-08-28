package com.f5.Airline.profiles;

import com.f5.Airline.countries.Country;
import com.f5.Airline.countries.CountryRepository;
import com.f5.Airline.exceptions.ProfileNotFoundException;
import com.f5.Airline.profiles.dto.ProfileResponseDTO;
import com.f5.Airline.profiles.dto.ProfileUpdateDTO;
import com.f5.Airline.users.User;
import com.f5.Airline.users.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;

    public ProfileService(ProfileRepository profileRepository,
                          UserRepository userRepository,
                          CountryRepository countryRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
    }

    public ProfileResponseDTO getMyProfile() {
        User currentUser = getCurrentUser();
        Profile profile = profileRepository.findByUser(currentUser)
                .orElseThrow(() -> new ProfileNotFoundException("Perfil no encontrado"));
        return ProfileMapper.toDTO(profile);
    }

    public ProfileResponseDTO updateMyProfile(ProfileUpdateDTO dto) {
        User currentUser = getCurrentUser();
        Profile profile = profileRepository.findByUser(currentUser)
                .orElseThrow(() -> new ProfileNotFoundException("Perfil no encontrado"));

        // Campos básicos
        profile.setPhone(dto.phone());
        profile.setAddress(dto.address());
        profile.setPhotoUrl(dto.photoUrl());

        // Manejo de country: si viene null, lo dejamos null
        if (dto.countryId() != null) {
            Country country = countryRepository.findById(dto.countryId())
                    .orElseThrow(() -> new RuntimeException("Country not found"));
            profile.setCountry(country);
        } else {
            profile.setCountry(null); // ✅ si no selecciona, queda null
        }

        profileRepository.save(profile);
        return ProfileMapper.toDTO(profile);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}

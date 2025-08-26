package com.f5.Airline.profiles;

import com.f5.Airline.exceptions.ProfileNotFoundException;
import com.f5.Airline.profiles.dto.ProfileMapper;
import com.f5.Airline.profiles.dto.ProfileRequestDTO;
import com.f5.Airline.profiles.dto.ProfileResponseDTO;
import com.f5.Airline.users.User;
import com.f5.Airline.users.UserRepository;
import com.f5.Airline.validation.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.profileMapper = profileMapper;
    }


    public ProfileResponseDTO createOrUpdateProfile(Long userId, ProfileRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ValidationException("Usuario no encontrado con ID: " + userId));

        Profile profile = profileRepository.findByUserId(userId)
                .orElse(new Profile());

        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setPhone(dto.getPhone());
        profile.setUser(user);

        Profile saved = profileRepository.save(profile);

        return new ProfileResponseDTO(
                saved.getId(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getPhone(),
                saved.getUser().getId()
        );
    }

    public ProfileResponseDTO getProfile(Long userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ValidationException("No existe perfil para el usuario con ID: " + userId));

        return new ProfileResponseDTO(
                profile.getId(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getPhone(),
                profile.getUser().getId()
        );
    }
    public ProfileResponseDTO getProfileByEmail(String email) {
        return profileRepository.findByEmail(email)
                .map(profileMapper::toResponseDTO)
                .orElseThrow(() -> new ProfileNotFoundException("No existe perfil con email: " + email));
    }

}

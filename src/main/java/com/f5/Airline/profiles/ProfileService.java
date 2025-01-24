package com.f5.Airline.profiles;

import com.f5.Airline.profiles.exceptions.ProfileNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Optional<Profile> updateProfile(Long id, Profile profileDetails) {
        return profileRepository.findById(id).map(existingProfile -> {
            existingProfile.setEmail(profileDetails.getEmail());
            existingProfile.setAddress(profileDetails.getAddress());
            existingProfile.setUser(profileDetails.getUser());
            existingProfile.setCountry(profileDetails.getCountry());
            return profileRepository.save(existingProfile);
        });
    }

    public void deleteProfile(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new ProfileNotFoundException("Perfil no encontrado con id: " + id);
        }
        profileRepository.deleteById(id);
    }
}

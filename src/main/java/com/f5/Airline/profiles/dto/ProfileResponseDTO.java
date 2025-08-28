package com.f5.Airline.profiles.dto;

public record ProfileResponseDTO(
        Long id,
        String username,
        String email,
        String phone,
        String address,
        String photoUrl,
        Long countryId,       // ID del país
        String countryName    // Nombre del país (útil para mostrar en el frontend)
) {}

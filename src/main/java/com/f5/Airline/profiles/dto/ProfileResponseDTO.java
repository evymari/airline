package com.f5.Airline.profiles.dto;


public record ProfileResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String phone,
        Long userId
) { }

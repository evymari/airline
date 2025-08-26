package com.f5.Airline.profiles.dto;

public record ProfileCreateDTO(
        String firstName,
        String lastName,
        String phone,
        String address,
        String country,
        String photoUrl,
        Long userId
) {}
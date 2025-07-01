package com.f5.Airline.users;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String photoUrl
) {}

package com.f5.Airline.register.dto;

import lombok.Data;


public record RegisterResponseDTO(
        Long id,
        String username,
        String email

) { }


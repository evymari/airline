package com.f5.Airline.users;


import com.f5.Airline.validation.ValidEmail;
import com.f5.Airline.validation.ValidUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto( @NotBlank(message = "El nombre de usuario es obligatorio")
                       @ValidUsername
                       String username,

                       @NotBlank(message = "El email es obligatorio")
                       @ValidEmail
                       String email,

                       @NotBlank(message = "La contraseña es obligatoria")
                       @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
                       String password,

                       String photoUrl
) {}
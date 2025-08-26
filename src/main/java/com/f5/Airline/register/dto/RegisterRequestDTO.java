package com.f5.Airline.register.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


public class RegisterRequestDTO {
    @NotBlank(message = "El username es obligatorio")
    private String username;

    @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    private String photoUrl;

    public @NotBlank(message = "El username es obligatorio") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "El username es obligatorio") String username) {
        this.username = username;
    }

    public @Email(message = "El email debe ser válido") @NotBlank(message = "El email es obligatorio") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "El email debe ser válido") @NotBlank(message = "El email es obligatorio") String email) {
        this.email = email;
    }

    public @NotBlank(message = "La contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "La contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String password) {
        this.password = password;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}


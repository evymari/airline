package com.f5.Airline.profiles.dto;

import jakarta.validation.constraints.Size;

public record ProfileUpdateDTO(

        @Size(max = 20, message = "El teléfono no puede tener más de 20 caracteres")
        String phone,

        @Size(max = 255, message = "La dirección no puede tener más de 255 caracteres")
        String address,

        @Size(max = 500, message = "La URL de la foto no puede tener más de 500 caracteres")
        String photoUrl,

        // Nuevo campo para país (opcional)
        Long countryId
) {

}

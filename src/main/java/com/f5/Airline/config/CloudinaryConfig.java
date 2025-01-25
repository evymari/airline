package com.f5.Airline.config;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        // Configuración del cliente de Cloudinary
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "tu_cloud_name", // Reemplaza con tu CLOUD_NAME
                "api_key", "tu_api_key",       // Reemplaza con tu API_KEY
                "api_secret", "tu_api_secret" // Reemplaza con tu API_SECRET
        ));
    }

}

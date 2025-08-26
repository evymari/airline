package com.f5.Airline.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
public class JwtConfig {
    @Bean
    public JwtEncoder jwtEncoder() {
        SecretKey key = new SecretKeySpec(
                "6uTrs0FaW82trpkmXY+B4YI58z2uUHSIbrzsp7xAHwQDPUwoXcs3RWzEXHAGavZfJdRFvUnLRRX1hUU1jPj0wA=="
                        .getBytes(StandardCharsets.UTF_8),
                "HmacSHA512"
        );
        return new NimbusJwtEncoder(new ImmutableSecret<>(key));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKey key = new SecretKeySpec(
                "6uTrs0FaW82trpkmXY+B4YI58z2uUHSIbrzsp7xAHwQDPUwoXcs3RWzEXHAGavZfJdRFvUnLRRX1hUU1jPj0wA=="
                        .getBytes(StandardCharsets.UTF_8),
                "HmacSHA512"
        );
        return NimbusJwtDecoder.withSecretKey(key).macAlgorithm(MacAlgorithm.HS512).build();
    }
}

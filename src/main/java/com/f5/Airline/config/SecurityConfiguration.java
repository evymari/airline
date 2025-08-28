package com.f5.Airline.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.f5.Airline.security.JpaUserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${api-endpoint}")
    private String endpoint;

    private final JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfiguration(JpaUserDetailsService userDetailsService) {
        this.jpaUserDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable()) // ❌ desactiva ventana emergente de Basic Auth
                .logout(out -> out
                        .logoutUrl(endpoint + "/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .authorizeHttpRequests(auth -> auth
                        // Permitir H2 console
                        .requestMatchers("/h2-console/**").permitAll()

                        // ✅ Endpoints públicos
                        .requestMatchers(HttpMethod.POST, endpoint + "/register/**").permitAll()
                        .requestMatchers(HttpMethod.POST, endpoint + "/auth/login/**").permitAll()
                        .requestMatchers(endpoint + "/public/**").permitAll()

                        // 🔒 Endpoints protegidos
                        .requestMatchers(endpoint + "/login").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(endpoint + "/admin/**").hasRole("ADMIN")
                        .requestMatchers(endpoint + "/private/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, endpoint + "/profiles/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, endpoint + "/profiles/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, endpoint + "/profiles/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, endpoint + "/profiles/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, endpoint + "/countries").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, endpoint + "/countries").hasRole("ADMIN")

                        // Todas las demás rutas requieren autenticación
                        .anyRequest().authenticated()
                )
                .userDetailsService(jpaUserDetailsService)
                .sessionManagement(session -> session
                        // ⚡️ Si luego usas JWT cambia esto a STATELESS
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );

        // Permitir iframes (necesario para H2 Console)
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // 👈 tu frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

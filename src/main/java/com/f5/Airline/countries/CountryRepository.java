package com.f5.Airline.countries;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    boolean existsByName(String name); // Verifica si un país existe por su nombre
    boolean existsByNameIgnoreCase(String name); // Verifica ignorando mayúsculas/minúsculas
}

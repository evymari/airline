package com.f5.Airline.countries;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

}

/*
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {



import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    boolean existsByName(String name);
    boolean existsByCode(String code);
    boolean existsByNameIgnoreCase(String name);

}

 */

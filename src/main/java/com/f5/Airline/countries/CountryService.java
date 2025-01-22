package com.f5.Airline.countries;

import com.f5.Airline.countries.exceptions.CountryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public List<Country> getAll() {
        List<Country> countries = repository.findAll();
        return countries;
    }

    public Country getById(Long id) {
        Country country = repository.findById(id).orElseThrow(() -> new CountryNotFoundException("Country not found"));
        return country;
    }

    public Country store(Country newCountry) {
        Country countryStored = repository.save(newCountry);
        return countryStored;
    }

}

/*
@Service
public class CountryService {
    CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }
    public List<Country> getAll(){
        List<Country> countries = repository.findAll();
        return countries;
    }
    public Country getById(Long id) {
        Country country = repository.findById(id).orElseThrow(() -> new CountryNotFoundException("Country not found"));
        return country;
    }
    public Country store(Country newCountry) {
        Country countryStored = repository.save(newCountry);
        return countryStored;
    }

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    public boolean existsByCode(String code) {
        return repository.existsByCode(code);
    }
    public Country update(Country country) {
        return repository.save(country);
    }
    public boolean existsByNameIgnoreCase(String name) {
        return repository.existsByNameIgnoreCase(name);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}

 */

package com.f5.Airline.countries;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-endpoint}/countries")
public class CountryController {

    private final CountryService services;

    public CountryController(CountryService services) {
        this.services = services;
    }

    // Obtener todos los países
    @GetMapping
    public List<Country> index() {
        return services.getAll();
    }

    // Obtener un país por ID
    @GetMapping("/{id}")
    public ResponseEntity<Country> show(@PathVariable("id") Long id) {
        Country country = services.getById(id);
        if (country != null) {
            return ResponseEntity.ok(country);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo país
    @PostMapping
    public ResponseEntity<Country> create(@RequestBody Country newCountry) {
        Country country = services.store(newCountry);
        return ResponseEntity.status(201).body(country);
    }

    // Actualizar un país
    @PutMapping("/{id}")
    public ResponseEntity<Country> update(@PathVariable Long id, @RequestBody Country updatedCountry) {
        Country country = services.update(id, updatedCountry);
        if (country != null) {
            return ResponseEntity.ok(country);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un país
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = services.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}



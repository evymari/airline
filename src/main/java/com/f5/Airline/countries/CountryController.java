package com.f5.Airline.countries;

import org.springframework.http.HttpStatus;
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

    @GetMapping
    public List<Country> index() {
        return services.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Country> show(@PathVariable("id") Long id) {
        Country country = services.getById(id);
        return ResponseEntity.ok(country);
    }

    @PostMapping
    public ResponseEntity<Country> create(@RequestBody Country newCountry) {
        Country country = services.store(newCountry);
        return ResponseEntity.status(HttpStatus.CREATED).body(country);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> update(@PathVariable("id") Long id, @RequestBody Country updatedCountry) {
        // Verificar si el país existe
        Country existingCountry = services.getById(id);
        if (existingCountry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Actualizar el país
        existingCountry.setName(updatedCountry.getName());
        Country updated = services.update(existingCountry);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Country existingCountry = services.getById(id);
        if (existingCountry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        services.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists/{name}")
    public ResponseEntity<Boolean> existsByName(@PathVariable("name") String name) {
        boolean exists = services.existsByName(name);
        return ResponseEntity.ok(exists);
    }
}

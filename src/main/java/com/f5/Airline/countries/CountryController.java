package com.f5.Airline.countries;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-endpoint}/countries")
public class CountryController {

    private CountryService services;

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
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(country);
    }

    @PostMapping
    public ResponseEntity<Country> create(@RequestBody Country newCountry) {
        Country country = services.store(newCountry);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(country);
    }

}

/*
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> create(@RequestBody Country newCountry) {
        // Estandarizar el nombre del país: primera letra en mayúscula, el resto en minúsculas
        String standardizedName = capitalizeFirstLetter(newCountry.getName());
        newCountry.setName(standardizedName);

        // Verificar si ya existe un país con el mismo nombre o código (ignorando diferencias de mayúsculas/minúsculas)
        boolean existsByName = services.existsByNameIgnoreCase(standardizedName); // Método ajustado
        boolean existsByCode = services.existsByCode(newCountry.getCode());

        if (existsByName || existsByCode) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)  // 409 - Conflicto
                    .body("El país con el mismo nombre o código ya existe.");
        }

        // Guardar el país en la base de datos
        Country country = services.store(newCountry);
        return ResponseEntity.status(HttpStatus.CREATED).body(country);
    }

    // Método auxiliar para capitalizar la primera letra de una cadena
    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable("id") Long id, @RequestBody Country updatedCountry) {
        // Buscar el país existente por ID
        Country existingCountry = services.getById(id);

        // Si el país no existe, devolver un 404 Not Found
        if (existingCountry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El país con ID " + id + " no existe.");
        }

        // Actualizar los campos del país (excepto el ID)
        existingCountry.setName(updatedCountry.getName());
        existingCountry.setCode(updatedCountry.getCode());
        // Puedes agregar más campos si es necesario

        // Guardar los cambios
        Country savedCountry = services.update(existingCountry);

        // Devolver el país actualizado con un 200 OK
        return ResponseEntity.ok(savedCountry);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Country country = services.getById(id);

        if (country == null) {
            return ResponseEntity.notFound().build();
        }

        services.delete(id);
        return ResponseEntity.noContent().build(); // 204 - Sin contenido
    }

}

*/

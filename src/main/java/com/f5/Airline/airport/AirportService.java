package com.f5.Airline.airport;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final List<Airport> airports = new ArrayList<>();

    // Add a new airport
    public Airport addAirport(Airport airport) {
        airports.add(airport);
        return airport;
    }

    // Get all airports
    public List<Airport> getAllAirports() {
        return airports;
    }

    // Find an airport by ID
    public Optional<Airport> getAirportById(Long id) {
        return airports.stream().filter(airport -> airport.getId().equals(id)).findFirst();
    }

    // Update an existing airport
    public Optional<Airport> updateAirport(Long id, Airport updatedAirport) {
        return getAirportById(id).map(airport -> {
            airport.setName(updatedAirport.getName());
            airport.setCity(updatedAirport.getCity());
            airport.setCodigo(updatedAirport.getCodigo());
            return airport;
        });
    }

    // Delete an airport
    public boolean deleteAirport(Long id) {
        return airports.removeIf(airport -> airport.getId().equals(id));
    }
}

package com.f5.Airline.flight;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("${api-endpoint}/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String departureDate,
            @RequestParam int seats) {

        try {
            LocalDateTime date = LocalDateTime.parse(departureDate);
            List<Flight> flights = flightService.searchFlights(origin, destination, date, seats);

            if (flights.isEmpty()) {
                return ResponseEntity.ok("No se encontraron vuelos con los criterios especificados.");
            }
            return ResponseEntity.ok(flights);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}

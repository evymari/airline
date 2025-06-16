package com.f5.Airline.flight;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchFlights(String origin, String destination, LocalDateTime date, int seats) {
        return flightRepository.findByOriginAndDestinationAndDepartureDateAfterAndAvailableSeatsGreaterThan(
                origin, destination, date, seats);
    }

    public void updateFlightStatus(Flight flight) {
        if (flight.getAvailableSeats() == 0 || flight.getDepartureDate().isBefore(LocalDateTime.now())) {
            flight.setStatus(false);
            flightRepository.save(flight);
        }
    }
}

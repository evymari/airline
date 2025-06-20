package com.f5.Airline.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByOriginAndDestinationAndDepartureDateAfterAndAvailableSeatsGreaterThan(
            String origin, String destination, LocalDateTime departureDate, int seats);
}

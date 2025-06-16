
package com.f5.Airline.reservation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api-endpoint}/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookFlight(
            @RequestParam Long userId,
            @RequestParam Long flightId,
            @RequestParam int seats) {
        String response = reservationService.bookFlight(userId, flightId, seats);
        return ResponseEntity.ok(response);
    }
}
package com.f5.Airline.reservation;

import com.f5.Airline.flight.Flight;
import com.f5.Airline.flight.FlightRepository;
import com.f5.Airline.users.User;
import com.f5.Airline.users.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class ReservationService {

    private final FlightRepository flightRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public ReservationService(FlightRepository flightRepository, ReservationRepository reservationRepository, UserRepository userRepository) {
        this.flightRepository = flightRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    public String bookFlight(Long userId, Long flightId, int seats) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (flight.getAvailableSeats() < seats) {
            return "No hay suficientes asientos disponibles";
        }

        if (flight.getDepartureDate().isBefore(LocalDateTime.now())) {
            return "El vuelo ya ha partido";
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - seats);
        flightRepository.save(flight);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setUser(user);
        reservation.setSeatsReserved(seats);
        reservation.setReservationTime(LocalDateTime.now());
        reservation.setReserveStatus("Pendiente");
        reservation.setConfirmed(false);
        reservationRepository.save(reservation);

        scheduleSeatRelease(reservation, seats);

        return "Reserva creada con éxito, pendiente de confirmación";
    }

    private void scheduleSeatRelease(Reservation reservation, int seats) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!reservation.isConfirmed()) {
                    Flight flight = reservation.getFlight();
                    flight.setAvailableSeats(flight.getAvailableSeats() + seats);
                    flightRepository.save(flight);

                    reservation.setReserveStatus("Cancelada");
                    reservationRepository.save(reservation);
                }
            }
        }, 15 * 60 * 1000); // 15 minutos
    }
}

package com.f5.Airline.reservation;

import com.f5.Airline.flight.Flight;
import com.f5.Airline.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Flight flight;

    @ManyToOne
    private User user;

    private int seatsReserved;
    private LocalDateTime reservationTime;
    private String reserveStatus; // "Pendiente", "Cancelada", etc.
    private boolean confirmed;

    public Reservation() {
    }

    public Reservation(Long id, Flight flight, User user, int seatsReserved, LocalDateTime reservationTime, String reserveStatus, boolean confirmed) {
        this.id = id;
        this.flight = flight;
        this.user = user;
        this.seatsReserved = seatsReserved;
        this.reservationTime = reservationTime;
        this.reserveStatus = reserveStatus;
        this.confirmed = confirmed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSeatsReserved() {
        return seatsReserved;
    }

    public void setSeatsReserved(int seatsReserved) {
        this.seatsReserved = seatsReserved;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getReserveStatus() {
        return reserveStatus;
    }

    public void setReserveStatus(String reserveStatus) {
        this.reserveStatus = reserveStatus;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
    // Getters y Setters
    // ...
}

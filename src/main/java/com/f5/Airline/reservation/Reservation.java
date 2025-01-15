package com.f5.Airline.reservation;

import com.f5.Airline.flight.Flight;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.util.Date;

@Getter
@Setter
public class Reservation {
    private Long id;
    private User user;
    private Flight flight;
    private Date reservationData;
   private String reserveStatus;

    public Reservation(Long id, String reserveStatus, Flight flight, User user, Date reservationData) {
        this.id = id;
        this.reserveStatus = reserveStatus;
        this.flight = flight;
        this.user = user;
        this.reservationData = reservationData;
    }
}
// Getters y Setters
/*public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public Usuario getUsuario() { return usuario; }
public void setUsuario(Usuario usuario) { this.usuario = usuario; }
public Vuelo getVuelo() { return vuelo; }
public void setVuelo(Vuelo vuelo) { this.vuelo = vuelo; }
public Date getFechaReserva() { return fechaReserva; }
public void setFechaReserva(Date fechaReserva) { this.fechaReserva = fechaReserva; }
public String getEstadoReserva() { return estadoReserva; }
public void setEstadoReserva(String estadoReserva) { this.estadoReserva = estadoReserva; }

// Métodos
public boolean realizarReserva() {
    System.out.println("Reserva realizada.");
    return true;
}

public boolean verificarDisponibilidad() {
    System.out.println("Verificando disponibilidad...");
    return true;
}
}*/
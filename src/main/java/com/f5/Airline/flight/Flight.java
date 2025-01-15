package com.f5.Airline.flight;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Flight {
    private Long id;
    private String origIN;
    private String destination;
    private Date dateFlight;
    private String state;
    private int spaceAvailable;



    public Flight(Long id, String origIN, String destination, Date dateFlight, String state, int spaceAvailable) {
        this.id = id;
        this.origIN = origIN;
        this.destination = destination;
        this.dateFlight = dateFlight;
        this.state = state;
        this.spaceAvailable = spaceAvailable;
    }
    // Métodos
    public void cambiarEstado() {
        System.out.println("Cambiando estado del vuelo...");
    }

    public List<Flight> buscarVuelo() {
        return new ArrayList<>(); // Retorna una lista de vuelos
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigIN() {
        return origIN;
    }

    public void setOrigIN(String origIN) {
        this.origIN = origIN;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDateFlight() {
        return dateFlight;
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = dateFlight;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getSpaceAvailable() {
        return spaceAvailable;
    }

    public void setSpaceAvailable(int spaceAvailable) {
        this.spaceAvailable = spaceAvailable;
    }
}
